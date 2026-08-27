package com.example.tienda.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductoRequest {
    @Size(min = 8, max = 30, message = "letra muy pequeña")
    private String nombre;
    @Positive
    private double precio;
    @Positive
    private int stock;
    @Nonnull
    private Long categoria;

    public ProductoRequest(String nombre, double precio, int stock, Long categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public Long getCategoria() {
        return categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

}
