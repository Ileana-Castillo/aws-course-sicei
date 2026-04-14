package com.escolar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.ServletException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /** 404 - Recurso no encontrado en el almacén en memoria */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorBody(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorBody("Recurso no encontrado: identificador inválido '" + ex.getValue() + "'"));
    }

    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<Map<String, Object>> handleNoHandler(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorBody("La ruta solicitada no existe"));
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<Map<String, Object>> handleServletException(ServletException ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : "";
        // Method Not Allowed
        if (msg.contains("Request method") || msg.contains("405") || msg.contains("not supported")) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body(errorBody("Método HTTP no permitido en esta ruta"));
        }
        // Cualquier otro ServletException → 400
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorBody("Petición inválida: " + msg));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> errores = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errores.put(e.getField(), e.getDefaultMessage()));
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "Error de validación");
        body.put("campos", errores);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleMalformedJson(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorBody("JSON inválido o tipos de dato incorrectos: " + ex.getMostSpecificCause().getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorBody("Error interno del servidor: " + ex.getMessage()));
    }

    private Map<String, Object> errorBody(String mensaje) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", mensaje);
        return body;
    }
}
