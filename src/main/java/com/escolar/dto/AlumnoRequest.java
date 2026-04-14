package com.escolar.dto;

import jakarta.validation.constraints.*;

public class AlumnoRequest {

    private Long id;

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

    @NotBlank(message = "El campo 'matricula' no puede estar vacío")
    @Size(min = 1, max = 50, message = "El campo 'matricula' debe tener entre 1 y 50 caracteres")
    private String matricula;

    @NotNull(message = "El campo 'promedio' no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = true,
                message = "El campo 'promedio' debe ser mayor o igual a 0.0")
    @DecimalMax(value = "10.0", inclusive = true,
                message = "El campo 'promedio' debe ser menor o igual a 10.0")
    private Double promedio;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public Double getPromedio() { return promedio; }
    public void setPromedio(Double promedio) { this.promedio = promedio; }
}
