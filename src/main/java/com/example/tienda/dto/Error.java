package com.example.tienda.dto;

public class Error {

    private int status;
    private String error;
    private String message;

    public Error(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

}
