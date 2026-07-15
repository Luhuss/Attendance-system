package com.school.attendance_system.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseDTO(
        Long id,
        @NotBlank(message = "El nombre del curso es obligatorio") String name,
        String description
) {}
