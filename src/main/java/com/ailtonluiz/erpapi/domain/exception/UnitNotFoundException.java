package com.ailtonluiz.erpapi.domain.exception;

public class UnitNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public UnitNotFoundException(String message) {
        super(message);
    }

    public UnitNotFoundException(Long id) {
        this(String.format("There is no unit registration with code %d", id));
    }
}
