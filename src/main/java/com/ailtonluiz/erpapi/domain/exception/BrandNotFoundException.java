package com.ailtonluiz.erpapi.domain.exception;

public class BrandNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public BrandNotFoundException(String message) {
        super(message);
    }

    public BrandNotFoundException(Long brandId) {
        this(String.format("There is no brand registration with code %d", brandId));
    }
}
