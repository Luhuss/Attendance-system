package com.school.attendance_system.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  DTO para recibir credenciales de login.
 *  Se envía en el body del reques como JSON:
 *  {
 *      "username": "admin",
 *      "password": "admin123"
 *  }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}
