package com.example.microservice_stock.adapters.driving.http.mapper;

import com.example.microservice_stock.adapters.driving.http.dto.response.CategoriesResponse;
import com.example.microservice_stock.domain.model.Categories;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoriesResponseMapper {
    // Mapea una entidad Categories a un objeto CategoriesResponse
    CategoriesResponse toCategoriesResponse(Categories categories);
    // Mapea una lista de entidades Categories a una lista de objetos CategoriesResponse
    List<CategoriesResponse> toCategoriesResponseList(List<Categories> categories);
}
