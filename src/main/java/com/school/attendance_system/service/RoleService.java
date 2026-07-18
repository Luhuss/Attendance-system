package com.school.attendance_system.service;

import com.school.attendance_system.dto.RoleDTO;
import com.school.attendance_system.mapper.RoleMapper;
import com.school.attendance_system.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    public RoleDTO create(RoleDTO dto) {
        log.info("Creando role: {}", dto.name());
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public List<RoleDTO> findAll() {
        log.debug("Obteniendo todos los roles");
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public RoleDTO findById(Long id) {
        log.debug("Buscando role: {}", id);
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> {
                    log.error("Rol no encontrado: {}", id);
                    return new EntityNotFoundException("Rol no encontrado");
                });
    }

    public void deleteById(Long id) {
        log.warn("Eliminando rol con id: {}", id);
        repository.deleteById(id);
        log.info("Rol eliminado: {}", id);
    }
}
