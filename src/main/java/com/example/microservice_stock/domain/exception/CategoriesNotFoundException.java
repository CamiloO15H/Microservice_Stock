package com.example.microservice_stock.domain.exception;

public class CategoriesNotFoundException extends RuntimeException {
    public CategoriesNotFoundException(String message) {
        super(message);
    }
}
