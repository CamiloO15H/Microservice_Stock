package com.example.Microservice_Stock.adapters.driven.jpa.mysql.mapper;

import com.example.Microservice_Stock.adapters.driven.jpa.mysql.entity.CategoriesEntity;
import com.example.Microservice_Stock.domain.model.Categories;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoriesEntityMapper {
    Categories toModel(CategoriesEntity CategoriesEntity);
    CategoriesEntity toEntity(Categories categories);
    List<Categories> toModelList(List<CategoriesEntity> categoriesEntity);
}
