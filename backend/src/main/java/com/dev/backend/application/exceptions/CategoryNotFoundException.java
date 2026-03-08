package com.dev.backend.application.exceptions;

public class CategoryNotFoundException extends ApplicationException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
