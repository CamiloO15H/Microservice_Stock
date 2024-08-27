package stock_microservices.domain.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stock_microservices.domain.model.Categories;

public interface CategoriesServicePort {

    Categories saveCategories(Categories categories);

    Page<Categories> getAllCategories(Pageable pageable);

    Categories getCategoriesByName(String name);

    Categories updateCategories(Categories categories);

    void deleteCategories(Long id);
}
