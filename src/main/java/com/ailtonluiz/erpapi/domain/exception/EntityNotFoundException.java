package com.ailtonluiz.erpapi.domain.exception;

public class EntityNotFoundException extends TransactionException {
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
