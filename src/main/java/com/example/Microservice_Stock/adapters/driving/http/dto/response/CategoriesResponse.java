package com.example.Microservice_Stock.adapters.driving.http.dto.response;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public class CategoriesResponse {
    private Long id;
    private String name;
    private String description;
}

//Lo que le VAMOS A RETORNAR A NUESTRO USUARIO CLIENTE