package com.example.microservice_stock.domain.spi;

import com.example.microservice_stock.domain.model.Categories;
import java.util.List;

//SPI Extender capacidades de nuestro sistema
public interface ICategoriesPersistencePort {

    void saveCategories(Categories categories);

    List<Categories> getAllCategories(Integer page, Integer size);

    Categories getCategoriesByName(String name);

    Categories updateCategories(Categories categories);

    void deleteCategories(Long id);
}
