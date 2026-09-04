package com.example.tienda.specification;

import com.example.tienda.model.Categoria;
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
            return criteriaBuilder.greaterThanOrEqualTo(root.get("precio"), precio);
        };
    }

    public static Specification<Producto> getProductosMenor(double precio) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("precio"), precio);
        };
    }

    public static Specification<Producto> filterByCategoria(Long categoriaId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("categoria").get("id"), categoriaId);
        };
    }

    public static Specification<Producto> filterByStock(int stock) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("stock"), stock);
        };
    }

    public static Specification<Producto> fitberByStockDisponible() {

        return (root, query, critero) -> {
            return critero.greaterThan(root.get("stock"), 0);
        };
    }

    public static Specification<Producto> fitberByPrecioMayor(double precio) {
        return (root, query, criteroBuilder) -> {
            return criteroBuilder.greaterThan(root.get("precio"), precio);
        };
    }

    
}
