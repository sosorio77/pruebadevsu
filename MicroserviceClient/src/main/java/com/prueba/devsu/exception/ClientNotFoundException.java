package com.prueba.devsu.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String message) {
        super(message);
    }
}
