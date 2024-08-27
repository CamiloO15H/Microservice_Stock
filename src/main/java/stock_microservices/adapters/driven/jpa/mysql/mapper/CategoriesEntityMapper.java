package stock_microservices.adapters.driven.jpa.mysql.mapper;

import stock_microservices.adapters.driven.jpa.mysql.entity.CategoriesEntity;
import stock_microservices.domain.model.Categories;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriesEntityMapper {
    Categories toModel(CategoriesEntity categoriesEntity);
    CategoriesEntity toEntity(Categories categories);
}
