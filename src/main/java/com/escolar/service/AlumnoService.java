package com.escolar.service;

import com.escolar.dto.AlumnoRequest;
import com.escolar.exception.ResourceNotFoundException;
import com.escolar.model.Alumno;
import com.escolar.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {

    private final AlumnoRepository repository;

    public AlumnoService(AlumnoRepository repository) {
        this.repository = repository;
    }

    public List<Alumno> obtenerTodos() {
        return repository.findAll();
    }

    public Alumno obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Alumno con id " + id + " no encontrado"));
    }

    public Alumno crear(AlumnoRequest request) {
        Alumno alumno = mapearRequest(request);
        // Usar el id provisto por el cliente
        alumno.setId(request.getId());
        return repository.save(alumno);
    }

    public Alumno actualizar(Long id, AlumnoRequest request) {
        obtenerPorId(id); // lanza 404 si no existe
        Alumno datosNuevos = mapearRequest(request);
        return repository.update(id, datosNuevos)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Alumno con id " + id + " no encontrado"));
    }

    public void eliminar(Long id) {
        boolean eliminado = repository.deleteById(id);
        if (!eliminado) {
            throw new ResourceNotFoundException(
                    "Alumno con id " + id + " no encontrado");
        }
    }

    private Alumno mapearRequest(AlumnoRequest request) {
        Alumno alumno = new Alumno();
        alumno.setNombres(request.getNombres().trim());
        alumno.setApellidos(request.getApellidos().trim());
        alumno.setMatricula(request.getMatricula().trim());
        alumno.setPromedio(request.getPromedio());
        return alumno;
    }
}
