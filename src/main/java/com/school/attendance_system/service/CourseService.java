package com.school.attendance_system.service;

import com.school.attendance_system.dto.CourseDTO;
import com.school.attendance_system.entity.Course;
import com.school.attendance_system.mapper.CourseMapper;
import com.school.attendance_system.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final CourseMapper mapper;

    public CourseDTO create(CourseDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public List<CourseDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public CourseDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
    }

    public CourseDTO update(Long id, CourseDTO dto) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
        course.setName(dto.name());
        course.setDescription(dto.description());
        return mapper.toDTO(repository.save(course));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
