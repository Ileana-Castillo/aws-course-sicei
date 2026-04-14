package com.escolar.repository;

import com.escolar.model.Alumno;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repositorio en memoria para Alumnos.
 * Los datos se almacenan en un ArrayList y se pierden al apagar la aplicación.
 * No existe conexión a ninguna base de datos.
 */
@Repository
public class AlumnoRepository {

    // Almacén principal en memoria
    private final List<Alumno> almacen = new ArrayList<>();

    // Generador de IDs auto-incremental thread-safe
    private final AtomicLong contadorId = new AtomicLong(1);

    /** Retorna todos los alumnos */
    public List<Alumno> findAll() {
        return new ArrayList<>(almacen);
    }

    /** Busca un alumno por su ID */
    public Optional<Alumno> findById(Long id) {
        return almacen.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    /** Guarda un nuevo alumno asignándole un ID único */
    public Alumno save(Alumno alumno) {
        alumno.setId(contadorId.getAndIncrement());
        almacen.add(alumno);
        return alumno;
    }

    /** Actualiza un alumno existente. Retorna el alumno actualizado o empty si no existe */
    public Optional<Alumno> update(Long id, Alumno datosNuevos) {
        return findById(id).map(existente -> {
            existente.setNombres(datosNuevos.getNombres());
            existente.setApellidos(datosNuevos.getApellidos());
            existente.setMatricula(datosNuevos.getMatricula());
            existente.setPromedio(datosNuevos.getPromedio());
            return existente;
        });
    }

    /** Elimina un alumno por ID. Retorna true si fue eliminado, false si no existía */
    public boolean deleteById(Long id) {
        return almacen.removeIf(a -> a.getId().equals(id));
    }
}
