package br.ifba.edu.mercadinho.model.exception.impl;

import org.springframework.http.HttpStatus;

import br.ifba.edu.mercadinho.model.exception.DefaultException;

public class ConflictException extends DefaultException {

    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

}
