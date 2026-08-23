package com.example.tienda.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.tienda.dto.CategoriaRequest;
import com.example.tienda.dto.CategoriaResponse;
import com.example.tienda.service.CategoriaService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/categoria")
    public ResponseEntity<CategoriaResponse> saveCategoria(@RequestBody CategoriaRequest categoriaRequest) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(this.categoriaService.guardarCategoria(categoriaRequest));
    }

    @GetMapping("/categoria")

    public ResponseEntity<List<CategoriaResponse>> getAll() {
        return ResponseEntity.status(201).body(this.categoriaService.getAll());
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<CategoriaResponse> getCategoriaById(@PathVariable Long id) {

        return ResponseEntity.ok(this.categoriaService.getById(id));
    }

    @PutMapping("/categoria/{id}")
    public ResponseEntity<CategoriaResponse> updateCategoria(@PathVariable Long id, @RequestBody CategoriaRequest cat) {
        return ResponseEntity.ok(HttpStatus.ACCEPTED).ok(this.categoriaService.updateCategoria(id, cat));
    }
}
