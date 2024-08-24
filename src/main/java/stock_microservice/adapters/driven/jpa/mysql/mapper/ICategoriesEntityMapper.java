package stock_microservice.adapters.driven.jpa.mysql.mapper;

import stock_microservice.adapters.driven.jpa.mysql.entity.CategoriesEntity;
import stock_microservice.domain.model.Categories;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoriesEntityMapper {
    Categories toModel(CategoriesEntity categoriesEntity);
    CategoriesEntity toEntity(Categories categories);
    List<Categories> toModelList(List<CategoriesEntity> categoriesEntity);
}
