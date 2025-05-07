package com.wilmer.prueba.infraestructure.error;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wilmer.prueba.infraestructure.error.model.FieldError;


@RestControllerAdvice
public class GlobalErorHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.badRequest()
        .body(Map.of(
            "error", e.getMessage(),
            "statusCode", HttpStatus.BAD_REQUEST.value()
        )
        );
    }

    @ExceptionHandler(RolDuplicateException.class)
    public ResponseEntity<?> handleRolDuplicateException(RolDuplicateException e) {
        return ResponseEntity.badRequest()
        .body(Map.of(
            "error", e.getMessage(),
            "statusCode", e.getStatus().value()
        )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleFiledsException(MethodArgumentNotValidException e){
         List<FieldError> filedErrors = e.getFieldErrors().stream()
         .map(field -> new FieldError(field.getField(), field.getDefaultMessage()))
         .toList();
         return ResponseEntity.badRequest().body(filedErrors);
    }
}
