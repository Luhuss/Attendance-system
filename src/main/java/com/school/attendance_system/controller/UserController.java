package com.school.attendance_system.controller;

import com.school.attendance_system.dto.UserDTO;
import com.school.attendance_system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) {
        log.info("Petición para crear usuario: {}", dto.username());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        log.debug("Perición para obtener todos los usuarios");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        log.debug("Petición para buscar usuario con id: {}", id);
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Petición para eliminar usuario con id: {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Ejemplo de busqueda personalizada
    @GetMapping("/search")
    public ResponseEntity<UserDTO> findByEmail(@RequestParam String email) {
        log.info("Petición para buscar usuario con email: {}", email);
        return ResponseEntity.ok(service.findByEmail(email));
    }

}
