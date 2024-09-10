package stock_microservices.adapters.driven.jpa.mysql.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import stock_microservices.adapters.driven.jpa.mysql.entity.CategoryEntity;
import stock_microservices.adapters.driven.jpa.mysql.mapper.CategoryEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.mapper.PaginationJPAMapper;
import stock_microservices.adapters.driven.jpa.mysql.repository.CategoryRepository;
import stock_microservices.domain.exceptions.EntityNotFoundException;
import stock_microservices.domain.model.Category;
import stock_microservices.domain.spi.CategoryPersistencePort;
import org.springframework.data.domain.Pageable;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.domain.utils.pagination.PaginationData;

@RequiredArgsConstructor
public class CategoryAdapter implements CategoryPersistencePort {

    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final PaginationJPAMapper paginationJPAMapper;

    @Override
    public void save(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public Category getCategory(Long id) {
        return categoryEntityMapper.toCategory(categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found")));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryEntityMapper.toCategory(categoryRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Category with name " + name + " not found")));
    }

    @Override
    public DomainPage<Category> getAllCategories(PaginationData paginationData) {
        Pageable pageable = paginationJPAMapper.toJPA(paginationData).createPageable();
        Page<CategoryEntity> returnedCategories = categoryRepository.findAll(pageable);
        return categoryEntityMapper.toDomainPage(returnedCategories);
    }

}