package stock_microservices.domain.spi;

import stock_microservices.domain.model.Category;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.domain.utils.pagination.PaginationData;

//SPI Extender capacidades de nuestro sistema
public interface CategoryPersistencePort {
    void save(Category category);
    Category getCategory(Long id);
    Category getCategoryByName(String name);
    DomainPage<Category> getAllCategories(PaginationData paginationData);
}
