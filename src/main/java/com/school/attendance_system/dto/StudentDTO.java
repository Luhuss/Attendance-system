package com.school.attendance_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentDTO(
        Long id,
        @NotBlank(message = "El nombre es obligatorio") String firstName,
        @NotBlank(message = "El apellido es obligatorio") String lastName,
        @Email(message = "Debe ser un mail válido") String email
) {}
