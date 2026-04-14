package com.escolar.repository;

import com.escolar.model.Profesor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio en memoria para Profesores.
 * El ID es provisto por el cliente en el cuerpo del POST.
 * Los datos se almacenan en un ArrayList sin conexión a base de datos.
 */
@Repository
public class ProfesorRepository {

    private final List<Profesor> almacen = new ArrayList<>();

    public List<Profesor> findAll() {
        return new ArrayList<>(almacen);
    }

    public Optional<Profesor> findById(Long id) {
        return almacen.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    /** Guarda un profesor usando el ID que ya trae el objeto (provisto por el cliente) */
    public Profesor save(Profesor profesor) {
        almacen.add(profesor);
        return profesor;
    }

    public Optional<Profesor> update(Long id, Profesor datosNuevos) {
        return findById(id).map(existente -> {
            existente.setNumeroEmpleado(datosNuevos.getNumeroEmpleado());
            existente.setNombres(datosNuevos.getNombres());
            existente.setApellidos(datosNuevos.getApellidos());
            existente.setHorasClase(datosNuevos.getHorasClase());
            return existente;
        });
    }

    public boolean deleteById(Long id) {
        return almacen.removeIf(p -> p.getId().equals(id));
    }
}
