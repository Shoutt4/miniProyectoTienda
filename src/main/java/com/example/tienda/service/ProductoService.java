package com.example.tienda.service;

import com.example.tienda.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import com.example.tienda.dto.ProductoRequest;
import com.example.tienda.dto.ProductoResponse;
import com.example.tienda.dto.productoByCategoria;
import com.example.tienda.exceotion.CategoriaException;
import com.example.tienda.exceotion.ProductoExcepption;
import com.example.tienda.model.Categoria;
import com.example.tienda.model.Producto;
import com.example.tienda.repository.ProductoRepository;

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
        return new productoByCategoria(pr.getId(), pr.getNombre(), pr.getPrecio(), pr.getStock(), pr.getCategoria());
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

        List<Producto> pr = this.productoRepository.getProdcutofiltadoPrecio(min, max);
        return pr.stream().map(p -> convertirProductoResponse(p)).toList();
    }

    public List<ProductoResponse> getProductosByCategoria(Long id) {
        List<Producto> newPr = this.productoRepository.getProductoById(id);
        return newPr.stream().map(p -> convertirProductoResponse(p)).toList();
    }
}
