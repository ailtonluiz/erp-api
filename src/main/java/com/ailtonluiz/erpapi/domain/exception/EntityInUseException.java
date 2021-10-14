package com.ailtonluiz.erpapi.domain.exception;

public class EntityInUseException extends TransactionException {
    private static final long serialVersionUID = 1L;

    public EntityInUseException(String message) {
        super(message);
    }
}
