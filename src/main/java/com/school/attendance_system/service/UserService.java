package com.school.attendance_system.service;

import com.school.attendance_system.dto.UserDTO;
import com.school.attendance_system.entity.Role;
import com.school.attendance_system.entity.User;
import com.school.attendance_system.mapper.UserMapper;
import com.school.attendance_system.repository.RoleRepository;
import com.school.attendance_system.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final RoleRepository roleRepository;

    public UserDTO create(UserDTO dto) {
        Role role = roleRepository.findById(dto.roleId())
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));
        User user = mapper.toEntity(dto);
        user.setRole(role);
        return mapper.toDTO(repository.save(user));
    }

    public List<UserDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public UserDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
