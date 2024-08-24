package stock_microservice.domain.api;

import stock_microservice.domain.model.Categories;
import java.util.List;

public interface ICategoriesServicePort {

    void saveCategories(Categories categories);

    List<Categories> getAllCategories(Integer page, Integer size,String sortDirection);

    Categories getCategoriesByName(String name);

    Categories updateCategories(Categories categories);

    void deleteCategories(Long id);

}
