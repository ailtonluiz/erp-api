package com.ailtonluiz.erpapi.domain.exception;

public class ClientNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(Long id) {
        this(String.format("There is no client registration with code %d", id));
    }
}
