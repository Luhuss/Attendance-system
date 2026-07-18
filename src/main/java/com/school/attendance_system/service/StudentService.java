package com.school.attendance_system.service;

import com.school.attendance_system.dto.StudentDTO;
import com.school.attendance_system.entity.Student;
import com.school.attendance_system.mapper.StudentMapper;
import com.school.attendance_system.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentDTO create(StudentDTO dto) {
        log.info("Creando estudiante con email: {}", dto.email());
        Student student = mapper.toEntity(dto);
        return  mapper.toDTO(repository.save(student));
    }

    public List<StudentDTO> findAll() {
        log.debug("Obteniendo todos los estudiantes");
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    public StudentDTO findById(Long id) {
        log.debug("Buscando estudiante con id: {}", id);
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> {
                    log.error("Estudiante no encontrado con id: {}", id);
                    return new EntityNotFoundException("Estudiante no encontrado");
                });
    }

    public StudentDTO update(Long id, StudentDTO dto) {
        log.info("Actualizando estudiante con id: {}", id);
        Student student = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("No se encontró estudiante con id: {}", id);
                    return new EntityNotFoundException("Estudiante no encontrado");
                });
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        return mapper.toDTO(repository.save(student));
    }

    public void delete(Long id) {
        log.warn("Eliminando estudiante con id: {}", id);
        repository.deleteById(id);
        log.info("Estudiante eliminado con id: {}", id);
    }
}
