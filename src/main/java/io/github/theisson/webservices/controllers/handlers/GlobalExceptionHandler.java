package io.github.theisson.webservices.controllers.handlers;

import java.time.Instant;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import io.github.theisson.webservices.dto.error.*;
import io.github.theisson.webservices.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatusCode status = HttpStatus.NOT_FOUND;

        StandardError err = new StandardError(
            Instant.now(),
            status.value(),
            "Resource not found",
            e.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatusCode status = HttpStatus.BAD_REQUEST;

        StandardError err = new StandardError(
            Instant.now(),
            status.value(),
            "Database exception",
            e.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatusCode status = HttpStatus.UNPROCESSABLE_CONTENT;

        ValidationError err = new ValidationError(
            Instant.now(),
            status.value(),
            "Validation exception",
            "Erro de validação nos campos",
            request.getRequestURI(),
            new ArrayList<>()
        );

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }
}
