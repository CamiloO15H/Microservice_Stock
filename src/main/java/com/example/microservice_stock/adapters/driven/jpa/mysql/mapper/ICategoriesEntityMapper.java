package com.example.microservice_stock.adapters.driven.jpa.mysql.mapper;

import com.example.microservice_stock.adapters.driven.jpa.mysql.entity.CategoriesEntity;
import com.example.microservice_stock.domain.model.Categories;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoriesEntityMapper {
    Categories toModel(CategoriesEntity CategoriesEntity);
    CategoriesEntity toEntity(Categories categories);
    List<Categories> toModelList(List<CategoriesEntity> categoriesEntity);
}
