package com.example.tienda.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.tienda.model.Producto;
import com.example.tienda.specification.ProductoSpecification;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long>, JpaSpecificationExecutor<Producto> {
        List<Producto> findByNombre(String nombre);

        List<Producto> findByCategoriaId(Long categoria);

        List<Producto> findByCategoriaIdOrderByPrecioAsc(Long categoria);

        List<Producto> findByCategoriaIdOrderByPrecioDesc(Long categoria);

        Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable page);

        Page<Producto> findAll(Pageable page);

        @Query("SELECT p FROM Producto p WHERE p.precio < :precio")
        List<Producto> getProductosPrecio(
                        @Param("precio") double precio);

        @Query("SELECT p  FROM Producto p where p.precio >= :min AND p.precio <= :max ")
        List<Producto> getProdcutofiltadoPrecio(
                        @Param("min") double min,
                        @Param("max") double max);

        
}
