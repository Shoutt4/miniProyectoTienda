package com.example.tienda.dto;

import jakarta.validation.constraints.NotBlank;

public class ResgisterResponse {
  
    private String message;
    private String usernmae;

    public ResgisterResponse(String message, String username) {
        this.message = message;
        this.usernmae = username;
    }

    public String getMessage() {
        return message;
    }

    public String getUsernmae() {
        return usernmae;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsernmae(String usernmae) {
        this.usernmae = usernmae;
    }
}
