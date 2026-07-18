package com.school.attendance_system.controller;

import com.school.attendance_system.dto.CourseDTO;
import com.school.attendance_system.service.CourseService;
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
@RequestMapping("/courses")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService service;

    @Operation(summary = "Crear curso", description = "Crea un nuevo curso en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación en los datos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
    })
    @PostMapping
    public ResponseEntity<CourseDTO> create(@Valid @RequestBody CourseDTO dto) {
        log.info("Petición para crear curso: {}", dto.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Obtener todos los cursos", description = "Devuelve la lista completa de cursos")
    @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll() {
        log.debug("Petición para obtener todos los cursos");
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Buscar curso por ID", description = "Devuelve un curso según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable Long id) {
        log.debug("Petición para busqueda de curso con id: {}", id);
        CourseDTO course = service.findById(id);
        log.info("Curso encontrado: {}", course.name());
        return ResponseEntity.ok(course);
    }

    @Operation(summary = "Actualizar curso", description = "Actualiza los datos de un curso existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
            @ApiResponse(responseCode = "400", description = "Error de validación en los datos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable Long id, @Valid @RequestBody CourseDTO dto) {
        log.info("Petición para actualizar curso con id: {}", id);
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar curso", description = "Elimina un curso por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.warn("Petición para eliminar curso con id: {}", id);
        service.delete(id);
        log.info("Curso eliminado: {}", id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar curso por nombre", description = "Devuelve cursos que coincidan con el nombre")
    @ApiResponse(responseCode = "200", description = "Cursos encontrados")
    // Ejemplo de busqueda personalizada
    @GetMapping("/search")
    public ResponseEntity<List<CourseDTO>> findByName(@RequestParam String name) {
        log.warn("Petición para busqueda de curso por nombre: {}", name);
        return ResponseEntity.ok(service.findByName(name));
    }

}
