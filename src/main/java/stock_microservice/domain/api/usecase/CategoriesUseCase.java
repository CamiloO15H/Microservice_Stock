package stock_microservice.domain.api.usecase;

import stock_microservice.domain.api.ICategoriesServicePort;
import stock_microservice.domain.model.Categories;
import stock_microservice.domain.spi.ICategoriesPersistencePort;

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
    public List<Categories> getAllCategories(Integer page,
                                             Integer size,
                                             String sortDirection) {
        return categoryPersistencePort.getAllCategories(page, size, sortDirection);}


    @Override
    public Categories updateCategories(Categories categories) {return categoryPersistencePort.updateCategories(categories);}

    @Override
    public void deleteCategories(Long id) {categoryPersistencePort.deleteCategories(id);}

}
