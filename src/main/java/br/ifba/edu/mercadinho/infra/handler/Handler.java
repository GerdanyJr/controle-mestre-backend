package br.ifba.edu.mercadinho.infra.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.ifba.edu.mercadinho.model.exception.DefaultException;
import br.ifba.edu.mercadinho.model.res.ErrorResponse;

@RestControllerAdvice
public class Handler {
    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<ErrorResponse> defaultExceptionHandler(DefaultException d) {
        return ResponseEntity
                .status(d.getStatus())
                .body(new ErrorResponse(d.getMessage(), d.getCode()));
    }
}
