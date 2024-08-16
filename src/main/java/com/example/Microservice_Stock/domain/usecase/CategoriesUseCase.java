package com.example.Microservice_Stock.domain.usecase;
import com.example.Microservice_Stock.domain.api.ICategoriesServicePort;
import com.example.Microservice_Stock.domain.model.Categories;
import com.example.Microservice_Stock.domain.spi.ICategoriesPersistencePort;
import java.util.List;

public class CategoriesUseCase implements ICategoriesServicePort {

    private final ICategoriesPersistencePort categoryPersistencePort;

    public CategoriesUseCase(ICategoriesPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategories(Categories categories) {
        categoryPersistencePort.saveCategories(categories);
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoryPersistencePort.getAllCategories();
    }

    @Override
    public Categories getCategory(Long name) {
        return categoryPersistencePort.getCategory(name);
    }

    @Override
    public void updateCategories(Categories categories) {
        categoryPersistencePort.updateCategories(categories);
    }

    @Override
    public void deleteCategories(Long name) {
        categoryPersistencePort.deleteCategories(name);
    }
}
