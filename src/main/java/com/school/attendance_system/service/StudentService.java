package com.school.attendance_system.service;

import com.school.attendance_system.dto.StudentDTO;
import com.school.attendance_system.entity.Student;
import com.school.attendance_system.mapper.StudentMapper;
import com.school.attendance_system.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentDTO create(StudentDTO dto) {
        Student student = mapper.toEntity(dto);
        return  mapper.toDTO(repository.save(student));
    }

    public List<StudentDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    public StudentDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado"));
    }

    public StudentDTO update(Long id, StudentDTO dto) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado"));
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        return mapper.toDTO(repository.save(student));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
