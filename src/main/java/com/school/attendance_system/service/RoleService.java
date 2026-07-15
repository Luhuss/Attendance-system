package com.school.attendance_system.service;

import com.school.attendance_system.dto.RoleDTO;
import com.school.attendance_system.entity.Role;
import com.school.attendance_system.mapper.RoleMapper;
import com.school.attendance_system.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    public RoleDTO create(RoleDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public List<RoleDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public RoleDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
