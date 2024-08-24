package com.example.microservice_stock.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateCategoriesRequest {
    private Long id;
    private String name;
    private String description;
}
