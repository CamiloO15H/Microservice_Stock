package stock_microservices.adapters.driven.jpa.mysql.mapper;

import org.mapstruct.Mapping;
import stock_microservices.adapters.driven.jpa.mysql.entity.CategoriesEntity;
import stock_microservices.domain.model.Categories;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriesEntityMapper {

    @Mapping(source = "id", target = "id")
    CategoriesEntity toEntity(Categories categories);
    Categories toModel(CategoriesEntity categoriesEntity);
}
