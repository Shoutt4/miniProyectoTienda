package com.example.tienda.exceotion;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errores.put(
                        error.getField(),
                        error.getDefaultMessage()));

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("message", "errores de variables");
        respuesta.put("errores", errores);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(AutchException.class)
    public ResponseEntity<Map<String, String>> response(AutchException ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("errors", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> erorLogin(BadCredentialsException ex) {
        Map<String, String> error = new HashMap<>();

        error.put("error", "error al intentar logear");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

}
