package stock_microservices.domain.spi;


import stock_microservices.domain.model.Category;
import stock_microservices.domain.model.Product;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.domain.utils.pagination.PaginationData;

import java.util.List;

public interface ProductPersistencePort {
    void save(Product product);
    DomainPage<Product> getAllProducts(PaginationData paginationData);
    List<Category> getProductCategories(Long id);
}
