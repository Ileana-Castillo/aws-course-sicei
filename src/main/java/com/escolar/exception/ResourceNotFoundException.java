package com.escolar.exception;

/**
 * Excepción lanzada cuando un recurso no se encuentra en el almacén en memoria.
 * Mapeada al código HTTP 404 Not Found.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
