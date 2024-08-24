package com.example.microservice_stock.adapters.driving.http.dto.request;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public class AddCategoriesRequest {
    private final String name;
    private final String description;
}

//Aca lo que debemos traer, no necesitamos el id
