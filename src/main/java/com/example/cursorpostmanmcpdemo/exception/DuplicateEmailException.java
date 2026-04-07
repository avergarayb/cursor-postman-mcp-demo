package com.example.cursorpostmanmcpdemo.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {
        super("Ya existe un usuario con el email: " + email);
    }
}
