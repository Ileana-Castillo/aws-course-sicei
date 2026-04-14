package com.escolar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Envoltorio genérico para todas las respuestas de la API.
 * Garantiza una estructura JSON consistente en todos los endpoints.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String mensaje;
    private T data;
    private Object errores;

    private ApiResponse() {}

    /** Respuesta exitosa con datos */
    public static <T> ApiResponse<T> ok(String mensaje, T data) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = true;
        r.mensaje = mensaje;
        r.data = data;
        return r;
    }

    /** Respuesta exitosa sin datos (ej: DELETE) */
    public static <T> ApiResponse<T> ok(String mensaje) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = true;
        r.mensaje = mensaje;
        return r;
    }

    /** Respuesta de error con detalle */
    public static <T> ApiResponse<T> error(String mensaje, Object errores) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = false;
        r.mensaje = mensaje;
        r.errores = errores;
        return r;
    }

    /** Respuesta de error simple */
    public static <T> ApiResponse<T> error(String mensaje) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = false;
        r.mensaje = mensaje;
        return r;
    }

    // Getters
    public boolean isSuccess() { return success; }
    public String getMensaje() { return mensaje; }
    public T getData() { return data; }
    public Object getErrores() { return errores; }
}
