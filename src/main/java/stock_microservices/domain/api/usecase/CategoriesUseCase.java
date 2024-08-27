package stock_microservices.domain.api.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stock_microservices.domain.api.CategoriesServicePort;
import stock_microservices.domain.model.Categories;
import stock_microservices.domain.spi.CategoriesPersistencePort;

@Service
public class CategoriesUseCase implements CategoriesServicePort {

    private final CategoriesPersistencePort categoryPersistencePort;

    @Autowired
    public CategoriesUseCase(CategoriesPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Categories saveCategories(Categories categories) {
        validateCategories(categories);
        categoryPersistencePort.saveCategories(categories);
        return categories;
    }

    @Override
    public Categories getCategoriesByName(String name) {
        return categoryPersistencePort.getCategoriesByName(name);
    }

    @Override
    public Page<Categories> getAllCategories(Pageable pageable) {
        return categoryPersistencePort.getAllCategories(pageable);
    }

    @Override
    public Categories updateCategories(Categories categories) {
        validateCategories(categories);
        return categoryPersistencePort.updateCategories(categories);
    }

    @Override
    public void deleteCategories(Long id) {
        categoryPersistencePort.deleteCategories(id);
    }

    private void validateCategories(Categories categories) {
        if (categories.getName() == null || categories.getName().isBlank() || categories.getName().length() > 50) {
            throw new IllegalArgumentException("Invalid category name");
        }
        if (categories.getDescription() == null || categories.getDescription().isBlank() || categories.getDescription().length() > 90) {
            throw new IllegalArgumentException("Invalid category description");
        }
    }
}
