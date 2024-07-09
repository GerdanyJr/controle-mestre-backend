package br.ifba.edu.mercadinho.model.exception.impl;

import org.springframework.http.HttpStatus;

import br.ifba.edu.mercadinho.model.exception.DefaultException;

public class NotFoundException extends DefaultException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
