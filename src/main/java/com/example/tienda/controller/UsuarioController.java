package com.example.tienda.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.tienda.service.UsuarioService;

@RestController
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
