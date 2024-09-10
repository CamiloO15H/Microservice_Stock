package stock_microservices.domain.api.usecase;

import org.springframework.stereotype.Service;
import stock_microservices.domain.exceptions.EmptyFieldException;
import stock_microservices.domain.exceptions.DuplicatedProductCategoryException;
import stock_microservices.domain.exceptions.NotEnoughCategoriesException;
import stock_microservices.domain.exceptions.OutOfBoundsException;
import stock_microservices.domain.model.Product;
import stock_microservices.domain.model.Category;
import stock_microservices.domain.spi.BrandPersistencePort;
import stock_microservices.domain.spi.CategoryPersistencePort;
import stock_microservices.domain.spi.ProductPersistencePort;
import stock_microservices.domain.api.ProductServicePort;
import stock_microservices.domain.utils.DomainConstants;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.domain.utils.pagination.PaginationData;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static stock_microservices.domain.utils.ValidationUtils.*;

@Service
public class ProductUseCase implements ProductServicePort {

    private final ProductPersistencePort productPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;
    private final BrandPersistencePort brandPersistencePort;

    public ProductUseCase(ProductPersistencePort productPersistencePort, CategoryPersistencePort categoryPersistencePort, BrandPersistencePort brandPersistencePort) {
        this.productPersistencePort = productPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void save(Product product) {
        validateName(product.getName(), DomainConstants.NAME_LENGTH_LIMIT);
        validateDescription(product.getDescription(), DomainConstants.BRAND_DESCRIPTION_LENGTH_LIMIT);
        validatePrice(product.getPrice());
        validateQuantity(product.getQuantity());
        if(product.getBrand() == null) throw new EmptyFieldException(DomainConstants.Field.BRAND.toString());
        brandPersistencePort.getBrand(product.getBrand().getId());

        if (product.getCategories() == null || product.getCategories().isEmpty())
            throw new NotEnoughCategoriesException(DomainConstants.PRODUCT_CATEGORY_INSUFFICIENT_MESSAGE);

        HashSet<Long> categoryIds = new HashSet<>();
        AtomicInteger prevSize = new AtomicInteger();
        product.getCategories().forEach(category -> {
            categoryPersistencePort.getCategory(category.getId());
            categoryIds.add(category.getId());
            if (categoryIds.size() == prevSize.get())
                throw new DuplicatedProductCategoryException(DomainConstants.PRODUCT_CATEGORY_DUPLICATED_MESSAGE);
            prevSize.set(categoryIds.size());
        });

        if (categoryIds.size() > DomainConstants.FIELD_CATEGORIES_LIMIT)
            throw new OutOfBoundsException(DomainConstants.CATEGORIES_LIMIT_REACHED_MESSAGE);

        productPersistencePort.save(product);
    }

    @Override
    public DomainPage<Product> getAllProducts(PaginationData paginationData) {
        return productPersistencePort.getAllProducts(paginationData);
    }

    @Override
    public List<Category> getProductCategories(Long id) {
        return productPersistencePort.getProductCategories(id);
    }
}
