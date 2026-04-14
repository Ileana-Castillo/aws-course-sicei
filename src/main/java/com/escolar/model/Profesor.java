package com.escolar.model;

/**
 * Entidad Profesor.
 * numeroEmpleado es de tipo Long para coincidir con los valores
 * numéricos que envían los tests (Constants.getRandomId()).
 */
public class Profesor {

    private Long id;
    private Long numeroEmpleado;
    private String nombres;
    private String apellidos;
    private Integer horasClase;

    public Profesor() {}

    public Profesor(Long id, Long numeroEmpleado, String nombres, String apellidos, Integer horasClase) {
        this.id = id;
        this.numeroEmpleado = numeroEmpleado;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.horasClase = horasClase;
    }

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
