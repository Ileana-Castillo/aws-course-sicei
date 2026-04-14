package com.escolar.service;

import com.escolar.dto.ProfesorRequest;
import com.escolar.exception.ResourceNotFoundException;
import com.escolar.model.Profesor;
import com.escolar.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        profesor.setId(request.getId());
        return repository.save(profesor);
    }

    public Profesor actualizar(Long id, ProfesorRequest request) {
        obtenerPorId(id);
        Profesor datosNuevos = mapearRequest(request);
        return repository.update(id, datosNuevos)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profesor con id " + id + " no encontrado"));
    }

    public void eliminar(Long id) {
        boolean eliminado = repository.deleteById(id);
        if (!eliminado) {
            throw new ResourceNotFoundException("Profesor con id " + id + " no encontrado");
        }
    }

    private Profesor mapearRequest(ProfesorRequest request) {
        Profesor profesor = new Profesor();
        profesor.setNumeroEmpleado(request.getNumeroEmpleado());
        profesor.setNombres(request.getNombres().trim());
        profesor.setApellidos(request.getApellidos().trim());
        profesor.setHorasClase(request.getHorasClase());
        return profesor;
    }
}
