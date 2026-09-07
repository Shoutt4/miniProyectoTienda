package com.example.tienda.exceotion;

public class ProductoNotFoundException extends RuntimeException {
    private String message;

    public ProductoNotFoundException(String message) {
        super(message);
    }
}
