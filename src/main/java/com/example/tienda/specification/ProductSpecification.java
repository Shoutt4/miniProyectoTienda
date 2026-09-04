package com.example.tienda.specification;

import com.example.tienda.model.Producto;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Producto> filterByCategoria() {
        return (root, query, cirterioBuilder) -> {
            return cirterioBuilder.equal(root.get("nombre"), "celular");
        };
    }

    public static Specification<Producto> finterByPreciMayor() {
        return (root, query, criterioBuilder) -> {

            return criterioBuilder.greaterThanOrEqualTo(root.get("precio"), 500);
        };
    }

    public static Specification<Producto> filterByPrecioMenos() {
        return (root, query, criterioBuilder) -> {
            return criterioBuilder.lessThanOrEqualTo(root.get("precio"), 2000);
        };
    }

    public static Specification<Producto> filterByStock() {
        return (root, query, specification) -> {
            return specification.equal(root.get("stock"), 0);
        };
    }
}
