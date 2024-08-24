package com.example.Microservice_Stock.domain.api;

import com.example.Microservice_Stock.domain.model.Categories;
import java.util.List;

public interface ICategoriesServicePort {

    void saveCategories(Categories categories);

    List<Categories> getAllCategories(Integer page, Integer size);

    Categories getCategoriesByName(String name);

    Categories updateCategories(Categories categories);

    void deleteCategories(Long id);

}
