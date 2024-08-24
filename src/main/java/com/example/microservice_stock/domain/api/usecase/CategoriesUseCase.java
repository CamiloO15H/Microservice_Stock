package com.example.microservice_stock.domain.api.usecase;

import com.example.microservice_stock.domain.api.ICategoriesServicePort;
import com.example.microservice_stock.domain.model.Categories;
import com.example.microservice_stock.domain.spi.ICategoriesPersistencePort;

import java.util.List;

public class CategoriesUseCase implements ICategoriesServicePort {

    private final ICategoriesPersistencePort categoryPersistencePort;

    public CategoriesUseCase(ICategoriesPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategories(Categories categories) {categoryPersistencePort.saveCategories(categories);}

    @Override
    public Categories getCategoriesByName(String name) {return categoryPersistencePort.getCategoriesByName(name);}

    @Override
    public List<Categories> getAllCategories(Integer page, Integer size) {return categoryPersistencePort.getAllCategories(page, size);}


    @Override
    public Categories updateCategories(Categories categories) {return categoryPersistencePort.updateCategories(categories);}

    @Override
    public void deleteCategories(Long id) {categoryPersistencePort.deleteCategories(id);}

}
