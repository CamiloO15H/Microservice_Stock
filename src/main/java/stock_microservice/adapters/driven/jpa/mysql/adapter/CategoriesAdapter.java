package stock_microservice.adapters.driven.jpa.mysql.adapter;

import org.springframework.data.domain.Sort;
import stock_microservice.adapters.driven.jpa.mysql.entity.CategoriesEntity;
import stock_microservice.adapters.driven.jpa.mysql.exception.CategoriesAlreadyExistsException;
import stock_microservice.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import stock_microservice.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import stock_microservice.adapters.driven.jpa.mysql.mapper.ICategoriesEntityMapper;
import stock_microservice.adapters.driven.jpa.mysql.repository.ICategoriesRepository;
import stock_microservice.domain.model.Categories;
import stock_microservice.domain.spi.ICategoriesPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class CategoriesAdapter implements ICategoriesPersistencePort {
    private final ICategoriesRepository categoriesRepository;
    private final ICategoriesEntityMapper categoriesEntityMapper;

    @Override
    public void saveCategories(Categories categories) {
        if (categoriesRepository.findByName(categories.getName()).isPresent()) {
            throw new CategoriesAlreadyExistsException();
        }
        categoriesRepository.save(categoriesEntityMapper.toEntity(categories));
    }

    @Override
    public List<Categories> getAllCategories(Integer page, Integer size, String sortDirection) {
        // Usa Sort.by para crear el objeto Sort y luego pásalo a PageRequest.of
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), "name");
        Pageable pagination = PageRequest.of(page, size, sort);

        List<CategoriesEntity> categories = categoriesRepository.findAll(pagination).getContent();
        if (categories.isEmpty()) {
            throw new NoDataFoundException();
        }
        return categoriesEntityMapper.toModelList(categories);
    }

    @Override
    public Categories getCategoriesByName(String name) {
        return categoriesEntityMapper.toModel(categoriesRepository.findByNameContaining(name)
                .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    public Categories updateCategories(Categories categories) {
        if (categoriesRepository.findById(categories.getId()).isEmpty()) {
            throw new ElementNotFoundException();
        }
        return categoriesEntityMapper.toModel(categoriesRepository.save(categoriesEntityMapper.toEntity(categories)));
    }

    @Override
    public void deleteCategories(Long id) {
        if (categoriesRepository.findById(id).isEmpty()) {
            throw new ElementNotFoundException();
        }
        categoriesRepository.deleteById(id);
    }
}
