package com.example.Microservice_Stock.adapters.driving.http.mapper;

import com.example.Microservice_Stock.adapters.driving.http.dto.request.AddCategoriesRequest;
import com.example.Microservice_Stock.adapters.driving.http.dto.request.UpdateCategoriesRequest;
import com.example.Microservice_Stock.domain.model.Categories;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoriesRequestMapper {

    // Mapea un objeto AddCategoriesRequest a una entidad Categories
    @Mapping(target = "id", ignore = true)  // Ignora el campo 'id' porque es autogenerado
    Categories addRequestToCategories(AddCategoriesRequest addCategoriesRequest);
    Categories updateRequestToCategories(UpdateCategoriesRequest updateCategoriesRequest);
}
