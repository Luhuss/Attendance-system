package com.school.attendance_system.service;

import com.school.attendance_system.dto.CourseDTO;
import com.school.attendance_system.dto.UserDTO;
import com.school.attendance_system.entity.Role;
import com.school.attendance_system.entity.User;
import com.school.attendance_system.mapper.UserMapper;
import com.school.attendance_system.repository.RoleRepository;
import com.school.attendance_system.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final RoleRepository roleRepository;

    public UserDTO create(UserDTO dto) {
        log.info("Creando usuario con email: {}", dto.email());
        Role role = roleRepository.findById(dto.roleId())
                .orElseThrow(() -> {
                    log.error("Rol no encontrado con id: {}", dto.roleId());
                    return new EntityNotFoundException("Rol no encontrado");
                });
        User user = mapper.toEntity(dto);
        user.setRole(role);
        log.debug("Usuario convertido a entidad: {}", user.getUsername());
        return mapper.toDTO(repository.save(user));
    }

    public List<UserDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public UserDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    // Busqueda personalizada
    public UserDTO findByEmail(String email) {
        log.debug("Buscando usuario con email: {}", email);
        return repository.findByEmail(email)
                .map(user -> {
                    log.info("Usuario encontrado con email: {}", email);
                    return mapper.toDTO(user);
                })
                .orElseThrow(() -> {
                    log.error("Usuario no encontrado con email: {}", email);
                    return new EntityNotFoundException("Usuario no encontrado");
                });
    }

    public void delete(Long id) {
        log.warn("Eliminando usuario con email: {}", id);
        repository.deleteById(id);
        log.info("Usuario eliminado con email: {}", id);
    }

}
