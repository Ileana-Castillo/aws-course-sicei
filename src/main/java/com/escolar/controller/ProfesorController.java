package com.escolar.controller;

import com.escolar.dto.ApiResponse;
import com.escolar.dto.ProfesorRequest;
import com.escolar.model.Profesor;
import com.escolar.service.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para el recurso Profesor.
 *
 * Endpoints disponibles:
 *   GET    /profesores          → lista todos los profesores        (200)
 *   GET    /profesores/{id}     → obtiene un profesor por ID        (200 | 404)
 *   POST   /profesores          → crea un nuevo profesor            (201 | 400)
 *   PUT    /profesores/{id}     → actualiza un profesor existente   (200 | 400 | 404)
 *   DELETE /profesores/{id}     → elimina un profesor               (200 | 404)
 */
@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    // ─── GET /profesores ───────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<ApiResponse<List<Profesor>>> listar() {
        List<Profesor> profesores = service.obtenerTodos();
        return ResponseEntity.ok(
                ApiResponse.ok("Profesores obtenidos correctamente", profesores)
        );
    }

    // ─── GET /profesores/{id} ──────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Profesor>> obtener(@PathVariable Long id) {
        Profesor profesor = service.obtenerPorId(id); // lanza 404 si no existe
        return ResponseEntity.ok(
                ApiResponse.ok("Profesor encontrado", profesor)
        );
    }

    // ─── POST /profesores ──────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<ApiResponse<Profesor>> crear(@Valid @RequestBody ProfesorRequest request) {
        Profesor nuevo = service.crear(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Profesor creado correctamente", nuevo));
    }

    // ─── PUT /profesores/{id} ──────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Profesor>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProfesorRequest request) {

        Profesor actualizado = service.actualizar(id, request); // lanza 404 si no existe
        return ResponseEntity.ok(
                ApiResponse.ok("Profesor actualizado correctamente", actualizado)
        );
    }

    // ─── DELETE /profesores/{id} ───────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id); // lanza 404 si no existe
        return ResponseEntity.ok(
                ApiResponse.ok("Profesor eliminado correctamente")
        );
    }
}
