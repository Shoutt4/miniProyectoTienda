package com.example.tienda.service;

import com.example.tienda.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import com.example.tienda.repository.ProductoRepository;
import com.example.tienda.specification.PrSpecification;
import com.example.tienda.service.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.tienda.dto.ProductoResponse;
import com.example.tienda.model.Producto;

@Service
public class ProductoService2 {
    private final ProductoService productoService;
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    public ProductoService2(ProductoRepository productoRepository, CategoriaRepository categoriaRepository,
            ProductoService productoService) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoService = productoService;
    }

    private ProductoResponse convertirProductoResponse(Producto producto) {
        return new ProductoResponse(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock());
    }

    public Page<ProductoResponse> getProductosFilterBySpecification(String nombre, Double min, Double max,
            Boolean estado, Long idCategoria, Integer minStock, Integer maxStock,
            Pageable page) {

        Specification<Producto> pr = Specification.allOf();
        if (min != null && min < 0) {
            return Page.empty(page);
        }

        if (max != null && max < 0) {
            return Page.empty(page);
        }

        if (min != null && max != null && min > max) {
            return Page.empty(page);
        }
        if (nombre != null) {
            pr = pr.and(PrSpecification.lookForName(nombre));
        }
        if (min != null && min >= 0) {
            pr = pr.and(PrSpecification.lookForPrice(min));
        }
        if (max != null && max >= 0) {
            pr = pr.and(PrSpecification.lookForPriceLess(max));
        }
        if (Boolean.TRUE.equals(estado) && (min == null)) {
            pr = pr.and(PrSpecification.lookForStock());
        }
        if (idCategoria != null && idCategoria > 0) {
            pr = pr.and(PrSpecification.lookForCategoria(idCategoria));
        }
        if (minStock != null && minStock >= 0) {
            pr = pr.and(PrSpecification.getMenorStock(maxStock));
        }
        if (maxStock != null && maxStock >= 0) {
            pr = pr.and(PrSpecification.getmayorStock(minStock));
        }
        return this.productoRepository.findAll(pr, page).map(this::convertirProductoResponse);
    }

}
