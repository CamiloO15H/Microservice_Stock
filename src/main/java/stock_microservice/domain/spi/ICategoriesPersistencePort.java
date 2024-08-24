package stock_microservice.domain.spi;

import stock_microservice.domain.model.Categories;
import java.util.List;

//SPI Extender capacidades de nuestro sistema
public interface ICategoriesPersistencePort {

    void saveCategories(Categories categories);

    List<Categories> getAllCategories(Integer page, Integer size, String sortDirection);

    Categories getCategoriesByName(String name);

    Categories updateCategories(Categories categories);

    void deleteCategories(Long id);
}
