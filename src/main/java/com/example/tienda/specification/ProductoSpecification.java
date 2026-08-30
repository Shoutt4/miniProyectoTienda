package com.example.tienda.specification;

import com.example.tienda.model.Producto;

import org.springframework.data.jpa.domain.Specification;

public class ProductoSpecification {

    public static Specification<Producto> nombreCotrains(String nombre) {
        return (root, query, criterioBuilder) -> {
            return criterioBuilder.like(root.get("nombre"), "%" + nombre + "%");
        };
    }

    public static Specification<Producto> precioMayorIgual(double precio) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get("precio"), precio);
        };
    }
}
