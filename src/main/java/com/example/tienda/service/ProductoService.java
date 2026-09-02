package com.example.tienda.service;

import com.example.tienda.repository.CategoriaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.tienda.dto.CategoriaResponse;
import com.example.tienda.dto.ProductoRequest;
import com.example.tienda.dto.ProductoResponse;
import com.example.tienda.dto.productoByCategoria;
import com.example.tienda.exceotion.CategoriaException;
import com.example.tienda.exceotion.ProductoExcepption;
import com.example.tienda.model.Categoria;
import com.example.tienda.model.Producto;
import com.example.tienda.repository.ProductoRepository;
import com.example.tienda.specification.ProductoSpecification;

import org.springframework.data.domain.Page;

import java.util.List;

@Service
public class ProductoService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Producto convertirProductoRequest(ProductoRequest producto, Categoria cat) {

        return new Producto(producto.getNombre(), producto.getPrecio(), producto.getStock(), cat);
    }

    public ProductoResponse crearProducto(ProductoRequest producto) {
        Categoria cat = this.categoriaRepository.findById(producto.getCategoria())
                .orElseThrow(() -> new CategoriaException("categoria no existe"));
        return convertirProductoResponse(this.productoRepository.save(convertirProductoRequest(producto, cat)));
    }

    public List<ProductoResponse> getProductoAll() {
        List<Producto> pr = this.productoRepository.findAll();
        return pr.stream().map(p -> convertirProductoResponse(p)).toList();
    }

    public productoByCategoria convertirConCategoria(Producto pr) {
        CategoriaResponse cat = new CategoriaResponse(pr.getCategoria().getId(), pr.getCategoria().getNombre(),
                pr.getCategoria().getDescripcion());
        return new productoByCategoria(pr.getId(), pr.getNombre(), pr.getPrecio(), pr.getStock(), cat);
    }

    public productoByCategoria getProductoById(Long id) {

        Producto pr = this.productoRepository.findById(id).orElseThrow(() -> new ProductoExcepption("id no existente"));
        return convertirConCategoria(pr);
    }

    private ProductoResponse convertirProductoResponse(Producto producto) {
        return new ProductoResponse(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock());
    }

    public List<ProductoResponse> buscarProducto(String nombre) {

        List<Producto> pr = this.productoRepository.findByNombre(nombre);
        return pr.stream().map(producto -> convertirProductoResponse(producto)).toList();
    }

    public List<ProductoResponse> getProductosByPrecio(double precio) {

        List<Producto> pr = this.productoRepository.getProductosPrecio(precio);
        return pr.stream().map(p -> convertirProductoResponse(p)).toList();
    }

    public List<ProductoResponse> getFilterProductos(double min, double max) {

        if ((min > 0 && min < max) && (max > 0 & max > min)) {
            List<Producto> pr = this.productoRepository.getProdcutofiltadoPrecio(min, max);
            return pr.stream().map(p -> convertirProductoResponse(p)).toList();
        } else {
            throw new ProductoExcepption("datos invalidos de min max deben ser positivos");
        }

    }

    public List<ProductoResponse> getProductosByCategoria(Long id) {

        if (this.categoriaRepository.existsById(id)) {
            List<Producto> newPr = this.productoRepository.findByCategoriaId(id);
            return newPr.stream().map(p -> convertirProductoResponse(p)).toList();
        } else {
            throw new CategoriaException("la categoria no existe");
        }
    }

    public List<ProductoResponse> getProductosOrderByAsc(Long id) {

        if (this.categoriaRepository.existsById(id)) {
            List<Producto> pr = this.productoRepository.findByCategoriaIdOrderByPrecioAsc(id);
            return pr.stream().map(p -> convertirProductoResponse(p)).toList();
        } else {
            throw new CategoriaException("categoria no encontra en productos");
        }
    }

    public List<ProductoResponse> getProductosOrderByDesc(Long id) {
        if (this.categoriaRepository.existsById(id)) {
            List<Producto> pr = this.productoRepository.findByCategoriaIdOrderByPrecioDesc(id);
            return pr.stream().map(p -> convertirProductoResponse(p)).toList();
        } else {
            throw new CategoriaException("categoria no exitenten");
        }
    }

    public Page<ProductoResponse> getPageable(Pageable page) {
        Page<Producto> pr = this.productoRepository.findAll(page);
        return pr.map(p -> convertirProductoResponse(p));
    }

    public Page<ProductoResponse> productoPr(String nombre, Pageable page) {
        Page<Producto> pr = this.productoRepository.findByNombreContainingIgnoreCase(nombre, page);
        return pr.map(this::convertirProductoResponse);
    }

    public Page<ProductoResponse> getSpeicfication(String nombre, Double min, Double max, Long categoriaId,
            Pageable page) {

        Specification<Producto> filtro = null;
        if (nombre != null) {
            filtro = ProductoSpecification.nombreCotrains(nombre);
        }
        if (min != null) {
            filtro = filtro == null ? ProductoSpecification.precioMayorIgual(min)
                    : filtro.and(ProductoSpecification.precioMayorIgual(min));
        }
        if (max != null) {
            filtro = filtro == null ? ProductoSpecification.getProductosMenor(max)
                    : filtro.and(ProductoSpecification.getProductosMenor(max));
        }
        if (categoriaId != null) {

            filtro = filtro == null ? ProductoSpecification.filterByCategoria(categoriaId)
                    : filtro.and(ProductoSpecification.filterByCategoria(categoriaId));
        }

        Page<Producto> prNew = this.productoRepository.findAll(filtro, page);
        return prNew.map(this::convertirProductoResponse);
    }

    public Page<ProductoResponse> getProductosPageables(String nombre, Double min, Double max, Integer stock,
            Long categoriaId,
            Pageable Page) {
        Specification<Producto> filtro = Specification.allOf();

        if (nombre != null) {
            filtro = filtro.and(ProductoSpecification.nombreCotrains(nombre));
        }
        if (min != null) {

            filtro = filtro.and(ProductoSpecification.precioMayorIgual(min));
        }
        if (max != null) {
            filtro = filtro.and(ProductoSpecification.getProductosMenor(max));
        }
        if (categoriaId != null) {
            filtro = filtro.and(ProductoSpecification.filterByCategoria(categoriaId));

        }
        if (stock != null) {
            filtro = filtro.and(ProductoSpecification.filterByStock(stock));
        }
        return this.productoRepository.findAll(filtro, Page).map(this::convertirProductoResponse);

    }

    public Page<ProductoResponse> getProductoByStock(Integer stock, Boolean estado, Pageable page) {
        Specification<Producto> filtro = Specification.allOf();
        if (stock != null) {
            filtro = filtro.and(ProductoSpecification.filterByStock(stock));
        }
        if (estado != null && estado) {
            filtro = filtro.and(ProductoSpecification.fitberByStockDisponible());
        }

        return this.productoRepository.findAll(filtro, page).map(this::convertirProductoResponse);
    }
}
