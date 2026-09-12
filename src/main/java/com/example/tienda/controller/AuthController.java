package com.example.tienda.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.tienda.dto.LoginRequest;
import com.example.tienda.dto.LoginResponse;
import com.example.tienda.dto.ResgisterResponse;
import com.example.tienda.dto.ResgistroRequest;
import com.example.tienda.security.JwtService;
import com.example.tienda.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        String token = this.jwtService.generateToken(authentication.getName());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    /*
     * @PostMapping("/register")
     * public ResponseEntity<LoginResponse> register(@RequestBody LoginRequest
     * request) {
     * 
     * Authentication authentication = this.authenticationManager
     * .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
     * request.getPassword()));
     * String token = this.jwtService.generateToken(authentication.getName());
     * 
     * return ResponseEntity.ok(new LoginResponse(token));
     * }
     */

    @PostMapping("/register")
    public ResponseEntity<ResgisterResponse> register(@Valid @RequestBody ResgistroRequest request) {
        return ResponseEntity.ok(this.authService.register(request));
    }
}
