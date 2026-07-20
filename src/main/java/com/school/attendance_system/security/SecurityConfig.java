package com.school.attendance_system.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    /**
     * Bean de Spring para encriptar contraseñas con BCrypt.
     *
     * - BCryptPasswordEncoder aplica hashing seguro con salt automático.
     * - El "cost factor" por defecto es 10 (se puede aumentar en producción).
     * - Este bean estará disponible en toda la aplicación para inyección.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
