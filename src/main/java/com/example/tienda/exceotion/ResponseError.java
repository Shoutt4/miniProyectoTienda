package com.example.tienda.exceotion;

public class ResponseError {
    private String campo;
    private String message;

    public ResponseError(String campo, String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCampo() {
        return campo;
    }

}
