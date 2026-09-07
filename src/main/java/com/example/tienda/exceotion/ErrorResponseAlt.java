package com.example.tienda.exceotion;

import java.time.LocalDateTime;

public class ErrorResponseAlt {
    private int status;
    private String error;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponseAlt(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now();
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
