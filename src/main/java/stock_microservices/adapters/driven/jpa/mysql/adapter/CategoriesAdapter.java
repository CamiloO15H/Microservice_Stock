package stock_microservices.adapters.driven.jpa.mysql.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import stock_microservices.adapters.driven.jpa.mysql.entity.CategoriesEntity;
import stock_microservices.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import stock_microservices.adapters.driven.jpa.mysql.mapper.CategoriesEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.repository.CategoriesRepository;
import stock_microservices.domain.model.Categories;
import stock_microservices.domain.spi.CategoriesPersistencePort;
import org.springframework.data.domain.Pageable;

@Component
public class CategoriesAdapter implements CategoriesPersistencePort {

    private final CategoriesRepository categoriesRepository;
    private final CategoriesEntityMapper categoriesEntityMapper;

    @Autowired
    public CategoriesAdapter(CategoriesRepository categoriesRepository, CategoriesEntityMapper categoriesEntityMapper) {
        this.categoriesRepository = categoriesRepository;
        this.categoriesEntityMapper = categoriesEntityMapper;
    }

    @Override
    public void saveCategories(Categories categories) {
        CategoriesEntity entity = categoriesEntityMapper.toEntity(categories);
        categoriesRepository.save(entity);
    }

    @Override
    public Page<Categories> getAllCategories(Pageable pageable) {
        Page<CategoriesEntity> entities = categoriesRepository.findAll(pageable);
        return entities.map(categoriesEntityMapper::toModel);
    }

    @Override
    public Categories getCategoriesByName(String name) {
        return categoriesEntityMapper.toModel(categoriesRepository.findByNameContaining(name)
                .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    public Categories updateCategories(Categories categories) {
        CategoriesEntity entity = categoriesEntityMapper.toEntity(categories);
        CategoriesEntity updatedEntity = categoriesRepository.save(entity);
        return categoriesEntityMapper.toModel(updatedEntity);
    }

    @Override
    public void deleteCategories(Long id) {
        categoriesRepository.deleteById(id);
    }
}