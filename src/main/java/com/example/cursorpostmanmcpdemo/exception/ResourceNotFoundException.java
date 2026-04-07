package com.example.cursorpostmanmcpdemo.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public static ResourceNotFoundException forId(String resource, Long id) {
        return new ResourceNotFoundException("%s no encontrado con id: %d".formatted(resource, id));
    }
}
