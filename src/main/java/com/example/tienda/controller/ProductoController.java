package com.example.tienda.controller;

import com.example.tienda.repository.ProductoRepository;
import org.springframework.web.bind.annotation.RestController;

import com.example.tienda.dto.ProductoRequest;
import com.example.tienda.dto.ProductoResponse;
import com.example.tienda.dto.productoByCategoria;
import com.example.tienda.model.Producto;
import com.example.tienda.service.ProductoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@RestController
public class ProductoController {

    private final ProductoRepository productoRepository;
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService, ProductoRepository productoRepository) {
        this.productoService = productoService;
        this.productoRepository = productoRepository;
    }

    @PostMapping("/producto")
    public ResponseEntity<ProductoResponse> createProducto(@Valid @RequestBody ProductoRequest producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productoService.crearProducto(producto));
    }

    @GetMapping("/productos")
    public ResponseEntity<List<ProductoResponse>> getAll() {
        return ResponseEntity.ok(this.productoService.getProductoAll());
    }

    @GetMapping("/productos/{id}/detalle")
    public ResponseEntity<productoByCategoria> getProductoById(@PathVariable Long id) {
        return ResponseEntity.ok(this.productoService.getProductoById(id));
    }

    @GetMapping("/nombres")
    public ResponseEntity<List<ProductoResponse>> getProductosBycoincidencia(@RequestParam String nombre) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.productoService.buscarProducto(nombre));
    }

    @GetMapping("/productos/filter")
    public ResponseEntity<List<ProductoResponse>> getProductoByPrecio(@RequestParam double precio) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.productoService.getProductosByPrecio(precio));
    }

    @GetMapping("/producto/precio")
    public ResponseEntity<List<ProductoResponse>> productosByMinMax(@RequestParam double min,
            @RequestParam double max) {
        return ResponseEntity.ok(this.productoService.getFilterProductos(min, max));
    }

    @GetMapping("/productos/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoResponse>> getProductoByCategoria(@PathVariable Long idCategoria) {
        return ResponseEntity.ok(this.productoService.getProductosByCategoria(idCategoria));
    }

    @GetMapping("/productos/ordebyAsc/{id}")
    public ResponseEntity<List<ProductoResponse>> getProductosAsc(@PathVariable Long id) {

        return ResponseEntity.ok(this.productoService.getProductosOrderByAsc(id));
    }

    @GetMapping("/Productos/Desc/{id}")
    public ResponseEntity<List<ProductoResponse>> getProdtuctosDesc(@PathVariable Long id) {
        return ResponseEntity.ok(this.productoService.getProductosOrderByDesc(id));
    }
}
