package com.indra.carrito.exceptions;

import com.indra.carrito.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionManagement {


    @ExceptionHandler(DatosException.class)
    public ResponseEntity<ExceptionResponseDto> handleExceptions(DatosException DatosException) {

        return new ResponseEntity<>(DatosException.getExceptionResponseObj(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put("Codigo", "3000");
            errors.put("Error", errorMessage);
            errors.put("Categoria", "Validaciones de entrada de parametros");
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
