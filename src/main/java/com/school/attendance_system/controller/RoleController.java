package com.school.attendance_system.controller;

import com.school.attendance_system.dto.RoleDTO;
import com.school.attendance_system.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Slf4j
public class RoleController {

    private final RoleService service;

    @Operation(summary = "Crear rol", description = "Crea un nuevo rol en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rol creado existosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación en los datos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @PostMapping
    public ResponseEntity<RoleDTO> create(@Valid @RequestBody RoleDTO dto) {
        log.info("Petición para crear rol: {}", dto.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Obtener todos los roles", description = "Devuelve la lista completa de roles")
    @ApiResponse(responseCode = "200", description = "Lista de roles obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll() {
        log.debug("Petición para obtener todos los roles");
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Buscar rol por ID", description = "Devuelve un rol según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol encontrado"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable Long id) {
        log.debug("Petición para buscar rol con id: {}", id);
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Eliminar rol", description = "Elimina un rol por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Rol eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.warn("Petición para eliminar rol con id: {}", id);
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
