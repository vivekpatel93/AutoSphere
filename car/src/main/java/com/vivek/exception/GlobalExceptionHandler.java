package com.vivek.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException r) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> dataIntegrityViolationException(DataIntegrityViolationException d) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(d.getMessage());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<String> incorrectPasswordException(IncorrectPasswordException i){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(i.getMessage());
    }

    @ExceptionHandler(IllegalStatementException.class)
    public ResponseEntity<String> illegalStatementException(IllegalStatementException i){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(i.getMessage());
    }
}
