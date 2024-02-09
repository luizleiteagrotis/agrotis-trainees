package com.agrotis.trainees.crud.service.exceptions;

public class CrudException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CrudException(String message) {
        super(message);
    }
}