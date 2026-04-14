package com.escolar.dto;

import jakarta.validation.constraints.*;

public class ProfesorRequest {

    private Long id;

    @NotNull(message = "El campo 'numeroEmpleado' no puede estar vacío")
    @Positive(message = "El campo 'numeroEmpleado' debe ser un número positivo")
    private Long numeroEmpleado;

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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getNumeroEmpleado() { return numeroEmpleado; }
    public void setNumeroEmpleado(Long numeroEmpleado) { this.numeroEmpleado = numeroEmpleado; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public Integer getHorasClase() { return horasClase; }
    public void setHorasClase(Integer horasClase) { this.horasClase = horasClase; }
}
