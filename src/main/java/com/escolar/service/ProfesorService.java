package com.escolar.service;

import com.escolar.dto.ProfesorRequest;
import com.escolar.exception.ResourceNotFoundException;
import com.escolar.model.Profesor;
import com.escolar.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de lógica de negocio para Profesores.
 */
@Service
public class ProfesorService {

    private final ProfesorRepository repository;

    public ProfesorService(ProfesorRepository repository) {
        this.repository = repository;
    }

    public List<Profesor> obtenerTodos() {
        return repository.findAll();
    }

    public Profesor obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profesor con id " + id + " no encontrado"));
    }

    public Profesor crear(ProfesorRequest request) {
        Profesor profesor = mapearRequest(request);
        return repository.save(profesor);
    }

    public Profesor actualizar(Long id, ProfesorRequest request) {
        // Verificar que existe antes de actualizar
        obtenerPorId(id);
        Profesor datosNuevos = mapearRequest(request);
        return repository.update(id, datosNuevos)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profesor con id " + id + " no encontrado"));
    }

    public void eliminar(Long id) {
        boolean eliminado = repository.deleteById(id);
        if (!eliminado) {
            throw new ResourceNotFoundException(
                    "Profesor con id " + id + " no encontrado");
        }
    }

    /** Convierte un ProfesorRequest DTO a entidad Profesor */
    private Profesor mapearRequest(ProfesorRequest request) {
        Profesor profesor = new Profesor();
        profesor.setNumeroEmpleado(request.getNumeroEmpleado().trim());
        profesor.setNombres(request.getNombres().trim());
        profesor.setApellidos(request.getApellidos().trim());
        profesor.setHorasClase(request.getHorasClase());
        return profesor;
    }
}
