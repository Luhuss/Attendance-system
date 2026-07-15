package com.school.attendance_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        Long id,
        @NotBlank(message = "El username es obligatorio") String username,
        @NotBlank(message = "La contraseña en obligatoria") String password,
        @Email(message = "Debe ser un email válido") String email,
        Long roleId
) {}
