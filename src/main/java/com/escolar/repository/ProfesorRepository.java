package com.escolar.repository;

import com.escolar.model.Profesor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repositorio en memoria para Profesores.
 * Los datos se almacenan en un ArrayList y se pierden al apagar la aplicación.
 * No existe conexión a ninguna base de datos.
 */
@Repository
public class ProfesorRepository {

    // Almacén principal en memoria
    private final List<Profesor> almacen = new ArrayList<>();

    // Generador de IDs auto-incremental thread-safe
    private final AtomicLong contadorId = new AtomicLong(1);

    /** Retorna todos los profesores */
    public List<Profesor> findAll() {
        return new ArrayList<>(almacen);
    }

    /** Busca un profesor por su ID */
    public Optional<Profesor> findById(Long id) {
        return almacen.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    /** Guarda un nuevo profesor asignándole un ID único */
    public Profesor save(Profesor profesor) {
        profesor.setId(contadorId.getAndIncrement());
        almacen.add(profesor);
        return profesor;
    }

    /** Actualiza un profesor existente. Retorna el profesor actualizado o empty si no existe */
    public Optional<Profesor> update(Long id, Profesor datosNuevos) {
        return findById(id).map(existente -> {
            existente.setNumeroEmpleado(datosNuevos.getNumeroEmpleado());
            existente.setNombres(datosNuevos.getNombres());
            existente.setApellidos(datosNuevos.getApellidos());
            existente.setHorasClase(datosNuevos.getHorasClase());
            return existente;
        });
    }

    /** Elimina un profesor por ID. Retorna true si fue eliminado, false si no existía */
    public boolean deleteById(Long id) {
        return almacen.removeIf(p -> p.getId().equals(id));
    }
}
