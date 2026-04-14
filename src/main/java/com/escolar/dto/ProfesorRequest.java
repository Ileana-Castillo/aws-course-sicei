package com.escolar.dto;

import jakarta.validation.constraints.*;

/**
 * DTO para crear/actualizar un Profesor.
 * Contiene validaciones de campos vacíos y tipos de dato.
 */
public class ProfesorRequest {

    @NotBlank(message = "El campo 'numeroEmpleado' no puede estar vacío")
    @Size(min = 2, max = 20, message = "El campo 'numeroEmpleado' debe tener entre 2 y 20 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9\\-]+$",
             message = "El campo 'numeroEmpleado' solo puede contener letras, números y guiones")
    private String numeroEmpleado;

    @NotBlank(message = "El campo 'nombres' no puede estar vacío")
    @Size(min = 2, max = 100, message = "El campo 'nombres' debe tener entre 2 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+$",
             message = "El campo 'nombres' solo puede contener letras y espacios")
    private String nombres;

    @NotBlank(message = "El campo 'apellidos' no puede estar vacío")
    @Size(min = 2, max = 100, message = "El campo 'apellidos' debe tener entre 2 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+$",
             message = "El campo 'apellidos' solo puede contener letras y espacios")
    private String apellidos;

    @NotNull(message = "El campo 'horasClase' no puede estar vacío")
    @Min(value = 1, message = "El campo 'horasClase' debe ser al menos 1")
    @Max(value = 60, message = "El campo 'horasClase' no puede superar 60 horas")
    private Integer horasClase;

    // Getters y Setters
    public String getNumeroEmpleado() { return numeroEmpleado; }
    public void setNumeroEmpleado(String numeroEmpleado) { this.numeroEmpleado = numeroEmpleado; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public Integer getHorasClase() { return horasClase; }
    public void setHorasClase(Integer horasClase) { this.horasClase = horasClase; }
}
