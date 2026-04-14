package com.escolar.repository;

import com.escolar.model.Alumno;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio en memoria para Alumnos.
 * El ID es provisto por el cliente en el cuerpo del POST.
 * Los datos se almacenan en un ArrayList sin conexión a base de datos.
 */
@Repository
public class AlumnoRepository {

    private final List<Alumno> almacen = new ArrayList<>();

    public List<Alumno> findAll() {
        return new ArrayList<>(almacen);
    }

    public Optional<Alumno> findById(Long id) {
        return almacen.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    /** Guarda un alumno usando el ID que ya trae el objeto (provisto por el cliente) */
    public Alumno save(Alumno alumno) {
        almacen.add(alumno);
        return alumno;
    }

    public Optional<Alumno> update(Long id, Alumno datosNuevos) {
        return findById(id).map(existente -> {
            existente.setNombres(datosNuevos.getNombres());
            existente.setApellidos(datosNuevos.getApellidos());
            existente.setMatricula(datosNuevos.getMatricula());
            existente.setPromedio(datosNuevos.getPromedio());
            return existente;
        });
    }

    public boolean deleteById(Long id) {
        return almacen.removeIf(a -> a.getId().equals(id));
    }
}
