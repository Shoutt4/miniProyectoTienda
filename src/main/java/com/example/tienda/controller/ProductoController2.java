package com.example.tienda.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.tienda.dto.ProductoResponse;
import com.example.tienda.service.ProductoService2;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController()
public class ProductoController2 {
    private final ProductoService2 productoService2;

    public ProductoController2(ProductoService2 productoService2) {
        this.productoService2 = productoService2;
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ProductoResponse>> getProductosBySpecification(
            @RequestParam(required = false) @Size(max = 100) String nombre,
            @RequestParam(required = false) @Positive Double min,
            @RequestParam(required = false) @Positive Double max,
            Pageable page) {
        return ResponseEntity.ok(this.productoService2.getProductosFilterBySpecification(nombre, min, max, page));
    }
}
