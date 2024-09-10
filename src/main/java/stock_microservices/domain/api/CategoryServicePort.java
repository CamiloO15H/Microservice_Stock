package stock_microservices.domain.api;

import stock_microservices.domain.model.Category;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.domain.utils.pagination.PaginationData;

public interface CategoryServicePort {
    void save(Category category);
    Category getCategory(Long id);
    DomainPage<Category> getAllCategories(PaginationData paginationData);
}
