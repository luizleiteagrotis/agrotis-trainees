package com.agrotis.trainees.crud.exception;

public class CrudException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CrudException(String message) {
        super(message);
    }
}