package com.example.tienda.service;

import org.springframework.stereotype.Service;
import com.example.tienda.dto.ProductoResponse;
import com.example.tienda.model.Producto;
import com.example.tienda.repository.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    private ProductoResponse convertirProductoResponse(Producto producto) {
        return new ProductoResponse(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock());
    }

    private Producto convertirProducto(ProductoResponse producto) {

        return new Producto(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock());
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

        List<Producto> pr = this.productoRepository.getProdcutofiltadoPrecio(min, max);
        return pr.stream().map(p -> convertirProductoResponse(p)).toList();
    }

    public List<ProductoResponse> getProductosByCategoria(Long id) {
        List<Producto> newPr = this.productoRepository.getProductoById(id);
        return newPr.stream().map(p -> convertirProductoResponse(p)).toList();
    }
}
