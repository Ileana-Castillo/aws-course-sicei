package com.escolar.model;

/**
 * Entidad Profesor.
 * Los datos se almacenan en memoria (array/lista) sin base de datos.
 */
public class Profesor {

    private Long id;
    private String numeroEmpleado;
    private String nombres;
    private String apellidos;
    private Integer horasClase;

    public Profesor() {}

    public Profesor(Long id, String numeroEmpleado, String nombres, String apellidos, Integer horasClase) {
        this.id = id;
        this.numeroEmpleado = numeroEmpleado;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.horasClase = horasClase;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroEmpleado() { return numeroEmpleado; }
    public void setNumeroEmpleado(String numeroEmpleado) { this.numeroEmpleado = numeroEmpleado; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public Integer getHorasClase() { return horasClase; }
    public void setHorasClase(Integer horasClase) { this.horasClase = horasClase; }
}
