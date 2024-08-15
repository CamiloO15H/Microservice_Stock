package com.example.Microservice_Stock.domain.spi;
import com.example.Microservice_Stock.domain.model.Categories;
import java.util.List;

//SPI Extender capacidades de nuestro sistema
public interface ICategoriesPersistencePort {
    Void saveCategories(Categories categories);

    List<Categories> getAllCategories();

    Categories getCategory(Long name);

    Void updateCategories(Categories categories);

    Void deleteCategories(Long name);
}
