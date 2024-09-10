package stock_microservices.domain.api.usecase;

import org.springframework.stereotype.Service;
import stock_microservices.domain.api.CategoryServicePort;
import stock_microservices.domain.exceptions.EntityAlreadyExistsException;
import stock_microservices.domain.exceptions.EntityNotFoundException;
import stock_microservices.domain.model.Category;
import stock_microservices.domain.spi.CategoryPersistencePort;
import stock_microservices.domain.utils.DomainConstants;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.domain.utils.pagination.PaginationData;

import static stock_microservices.domain.utils.ValidationUtils.validateDescription;
import static stock_microservices.domain.utils.ValidationUtils.validateName;

@Service
public class CategoryUseCase implements CategoryServicePort {

    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void save(Category category) {
        validateName(category.getName(), DomainConstants.NAME_LENGTH_LIMIT);
        validateDescription(category.getDescription(), DomainConstants.CATEGORY_DESCRIPTION_LENGTH_LIMIT);
        try {
            categoryPersistencePort.getCategoryByName(category.getName());
            throw new EntityAlreadyExistsException(Category.class.getSimpleName(), category.getName());
        } catch (EntityNotFoundException e) {
            categoryPersistencePort.save(category);
        }
    }

    @Override
    public Category getCategory(Long id) {
        return categoryPersistencePort.getCategory(id);
    }

    @Override
    public DomainPage<Category> getAllCategories(PaginationData paginationData) {
        return categoryPersistencePort.getAllCategories(paginationData);
    }
}
