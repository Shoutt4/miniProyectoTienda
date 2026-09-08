package com.example.tienda.exceotion;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

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
                .body(new Error(400, "Erroe en producto", excepption.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseError> exceptionArguments(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(new ResponseError(ex.getMessage(), ex.getLocalizedMessage()));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ResponseError> exceptionParametros(HandlerMethodValidationException ex) {

        return ResponseEntity.badRequest().body(new ResponseError(ex.getDetailMessageCode(), "error en parametros"));
    }

    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ResponseError> errorNormal(ProductoNotFoundException res) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseError("error", res.getMessage()));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponseAlt> errorElaborado(ApiException err) {
        return ResponseEntity.status(err.getStatus()).body(new ErrorResponseAlt(err));
    }
}
