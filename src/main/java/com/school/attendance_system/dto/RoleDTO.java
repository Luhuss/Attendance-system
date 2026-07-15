package com.school.attendance_system.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleDTO(
        Long id,
        @NotBlank(message = "El nombre del rol es obligatorio") String name,
        String description
) {}
