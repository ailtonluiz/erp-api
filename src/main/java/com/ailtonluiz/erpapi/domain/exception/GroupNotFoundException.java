package com.ailtonluiz.erpapi.domain.exception;

public class GroupNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(Long id) {
        this(String.format("There is no group registration with code %d", id));
    }
}
