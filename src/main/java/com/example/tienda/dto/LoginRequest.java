package com.example.tienda.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {
    @NotBlank(message = "el usuario es obligatirio")
    private String username;
    @NotBlank(message = "la contraseña es obligatoria")
    @Size(min = 8, max = 16, message = "EL MINIMO DE CARACTERES ES DE 8 A 16 LETRAS ")
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
