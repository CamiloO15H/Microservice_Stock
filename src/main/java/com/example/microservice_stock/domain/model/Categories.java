package com.example.microservice_stock.domain.model;

import com.example.microservice_stock.domain.exception.InvalidFieldException;
import static java.util.Objects.requireNonNull;

public class Categories { //IDENTIDAD

    private final Long id;
    private final String name;
    private final String description;

    public Categories(Long id, String name, String description) {
        if (name.trim().isEmpty() || name.length() > 50) {
            throw new InvalidFieldException("Category name cannot be empty and must be 50 characters or less.");
        }
        if (description.trim().isEmpty() || description.length() > 90) {
            throw new InvalidFieldException("Category description cannot be empty and must be 90 characters or less.");
        }
        this.id = id;
        this.name = requireNonNull(name, "Category name cannot be null.");
        this.description = requireNonNull(description, "Category description cannot be null.");
    }

    public Long getId() {return id;}

    public String getName() {return name;}

    public String getDescription() {return description;}

}

