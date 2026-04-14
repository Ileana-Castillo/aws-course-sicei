package com.escolar.controller;

import com.escolar.dto.AlumnoRequest;
import com.escolar.dto.ApiResponse;
import com.escolar.model.Alumno;
import com.escolar.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para el recurso Alumno.
 *
 * Endpoints disponibles:
 *   GET    /alumnos          → lista todos los alumnos          (200)
 *   GET    /alumnos/{id}     → obtiene un alumno por ID         (200 | 404)
 *   POST   /alumnos          → crea un nuevo alumno             (201 | 400)
 *   PUT    /alumnos/{id}     → actualiza un alumno existente    (200 | 400 | 404)
 *   DELETE /alumnos/{id}     → elimina un alumno                (200 | 404)
 */
@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService service;

    public AlumnoController(AlumnoService service) {
        this.service = service;
    }

    // ─── GET /alumnos ──────────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<ApiResponse<List<Alumno>>> listar() {
        List<Alumno> alumnos = service.obtenerTodos();
        return ResponseEntity.ok(
                ApiResponse.ok("Alumnos obtenidos correctamente", alumnos)
        );
    }

    // ─── GET /alumnos/{id} ─────────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Alumno>> obtener(@PathVariable Long id) {
        Alumno alumno = service.obtenerPorId(id); // lanza 404 si no existe
        return ResponseEntity.ok(
                ApiResponse.ok("Alumno encontrado", alumno)
        );
    }

    // ─── POST /alumnos ─────────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<ApiResponse<Alumno>> crear(@Valid @RequestBody AlumnoRequest request) {
        Alumno nuevo = service.crear(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Alumno creado correctamente", nuevo));
    }

    // ─── PUT /alumnos/{id} ─────────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Alumno>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody AlumnoRequest request) {

        Alumno actualizado = service.actualizar(id, request); // lanza 404 si no existe
        return ResponseEntity.ok(
                ApiResponse.ok("Alumno actualizado correctamente", actualizado)
        );
    }

    // ─── DELETE /alumnos/{id} ──────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id); // lanza 404 si no existe
        return ResponseEntity.ok(
                ApiResponse.ok("Alumno eliminado correctamente")
        );
    }
}
