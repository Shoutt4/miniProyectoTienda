package com.example.tienda.dto;

public class CategoriaResponse {
    private Long id;
    private String nombre;

    private String descripcion;

    public CategoriaResponse(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}
