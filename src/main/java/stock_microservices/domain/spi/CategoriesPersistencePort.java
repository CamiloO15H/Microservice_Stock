package stock_microservices.domain.spi;

import org.springframework.data.domain.Pageable;
import stock_microservices.domain.model.Categories;
import org.springframework.data.domain.Page;
//SPI Extender capacidades de nuestro sistema
public interface CategoriesPersistencePort {

    void saveCategories(Categories categories);

    Page<Categories> getAllCategories(Pageable pageable);

    Categories getCategoriesByName(String name);

    Categories updateCategories(Categories categories);

    void deleteCategories(Long id);
}
