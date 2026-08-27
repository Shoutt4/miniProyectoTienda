package com.example.tienda.exceotion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.tienda.dto.Error;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoriaException.class)
    public ResponseEntity<Error> exceptionCategoria(CategoriaException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Error(401, "error en operacion de categoria", exception.getMessage()));

    }

    @ExceptionHandler(ProductoExcepption.class)
    public ResponseEntity<Error> exceptionProducto(ProductoExcepption excepption) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Error(401, "Erroe en producto", excepption.getMessage()));
    }
}
