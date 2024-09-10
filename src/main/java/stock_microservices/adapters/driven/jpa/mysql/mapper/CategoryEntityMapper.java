package stock_microservices.adapters.driven.jpa.mysql.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import stock_microservices.adapters.driven.jpa.mysql.entity.CategoryEntity;
import stock_microservices.domain.model.Category;
import org.mapstruct.Mapper;
import stock_microservices.domain.utils.pagination.DomainPage;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryEntityMapper {
    @Mapping(target = "products", ignore = true)
    CategoryEntity toEntity(Category category);

    @Mapping(target = "products", ignore = true)
    Category toCategory(CategoryEntity categoryEntity);

    List<Category> toCategories(List<CategoryEntity> categoryEntities);

    @Mapping(target = "page", source = "number")
    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "count", source = "numberOfElements")
    @Mapping(target = "totalCount", source = "totalElements")
    @Mapping(target = "content", source = "content")
    DomainPage<Category> toDomainPage(Page<CategoryEntity> categoryEntities);
}
