package com.example.tienda.dto;

import com.example.tienda.dto.ProductoResponse;
import com.example.tienda.model.Categoria;

public class productoByCategoria {
    private Long id;
    private String nombre;
    private double precio;
    private int stock;
    private Categoria categoria;

    public productoByCategoria() {

    }

    public productoByCategoria(

            Long id,
            String nombre,
            double precio,
            int stock,
            Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;

    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Long getId() {
        return id;
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
