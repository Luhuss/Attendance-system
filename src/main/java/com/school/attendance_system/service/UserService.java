package com.school.attendance_system.service;

import com.school.attendance_system.dto.UserDTO;
import com.school.attendance_system.entity.Role;
import com.school.attendance_system.entity.User;
import com.school.attendance_system.exception.EntityNotFoundException;
import com.school.attendance_system.mapper.UserMapper;
import com.school.attendance_system.repository.RoleRepository;
import com.school.attendance_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO create(UserDTO dto) {
        log.info("Creando usuario con email: {}", dto.email());

        Role role = roleRepository.findById(dto.roleId())
                .orElseThrow(() -> {
                    log.error("Rol no encontrado con id: {}", dto.roleId());
                    return new EntityNotFoundException("Rol no encontrado");
                });

        User user = mapper.toEntity(dto);

        // Encriptar contraseña antes de guardar
        if (dto.password() != null && !dto.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.password()));
        } else {
            log.warn("Contraseña vacía para usuario con email: {}", dto.email());
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }

        user.setRole(role);

        log.debug("Usuario convertido a entidad: {}", user.getUsername());

        User saved = userRepository.save(user);
        log.info("Usuario guardado con id: {}", saved.getId());

        return mapper.toDTO(saved);
    }

    public UserDTO update(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        user.setEmail(dto.email());
        user.setUsername(dto.username());

        // Solo actualizar contraseña si viene en el DTO
        if (dto.password() != null && !dto.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.password()));
        }
        return mapper.toDTO(userRepository.save(user));
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(mapper::toDTO).toList();
    }

    public UserDTO findById(Long id) {
        return userRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    public UserDTO findByEmail(String email) {
        log.debug("Buscando usuario con email: {}", email);
        return userRepository.findByEmail(email)
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
        log.warn("Eliminando usuario con id: {}", id);
        userRepository.deleteById(id);
        log.info("Usuario eliminado con id: {}", id);
    }
}
