package com.example.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.tienda.model.Producto;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
        List<Producto> findByNombre(String nombre);

        List<Producto> findByCategoriaId(Long categoria);

        List<Producto> findByCategoriaIdOrderByPrecioAsc(Long categoria);

        List<Producto> findByCategoriaIdOrderByPrecioDesc(Long categoria);

        @Query("SELECT p FROM Producto p WHERE p.precio < :precio")
        List<Producto> getProductosPrecio(
                        @Param("precio") double precio);

        @Query("SELECT p  FROM Producto p where p.precio >= :min AND p.precio <= :max ")
        List<Producto> getProdcutofiltadoPrecio(
                        @Param("min") double min,
                        @Param("max") double max);

}
