package com.escolar.exception;

import com.escolar.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Manejador global de excepciones.
 * Captura errores de validación, recursos no encontrados y errores inesperados,
 * y devuelve siempre una respuesta JSON con el código HTTP correcto.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 404 - Recurso no encontrado
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    /**
     * 400 - Errores de validación Bean Validation (@Valid)
     * Devuelve un mapa con campo → mensaje de error.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Error de validación en los datos enviados", errores));
    }

    /**
     * 400 - JSON malformado o tipo de dato incorrecto en el cuerpo de la petición
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleMalformedJson(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(
                        "El cuerpo de la petición contiene JSON inválido o tipos de dato incorrectos. " +
                        "Verifique que los números no vengan como texto y que el JSON esté bien formado."
                ));
    }

    /**
     * 500 - Cualquier otro error inesperado
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Error interno del servidor: " + ex.getMessage()));
    }
}
