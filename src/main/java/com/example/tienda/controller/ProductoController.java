package com.example.tienda.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.tienda.dto.ProductoResponse;
import com.example.tienda.service.ProductoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@RestController
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
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
}
