package com.example.tienda.specification;

import com.example.tienda.model.Producto;
import org.springframework.data.jpa.domain.Specification;

public class PrSpecification {

    public static Specification<Producto> lookForName(String nombre) {
        return (root, query, criterioBuilder) -> {
            return criterioBuilder.equal(root.get("nombre"), nombre);
        };
    }

    public static Specification<Producto> lookForPrice(double min) {
        return (root, query, criterioBuilder) -> {
            return criterioBuilder.greaterThanOrEqualTo(root.get("precio"), min);
        };
    }

    public static Specification<Producto> lookForPriceLess(double max) {
        return (root, query, criterioBuilder) -> {
            return criterioBuilder.lessThanOrEqualTo(root.get("precio"), max);
        };
    }

    public static Specification<Producto> lookForStock() {
        return (root, query, criterioBuilder) -> {
            return criterioBuilder.greaterThan(root.get("stock"), 0);
        };
    }
}
