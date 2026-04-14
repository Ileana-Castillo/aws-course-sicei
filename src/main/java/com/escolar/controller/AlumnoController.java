package com.escolar.controller;

import com.escolar.model.Alumno;
import com.escolar.service.AlumnoService;
import com.escolar.dto.AlumnoRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService service;

    public AlumnoController(AlumnoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Alumno>> listar() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Alumno> crear(@Valid @RequestBody AlumnoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody AlumnoRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
