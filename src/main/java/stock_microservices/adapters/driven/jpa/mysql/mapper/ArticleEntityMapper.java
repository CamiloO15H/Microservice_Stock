package stock_microservices.adapters.driven.jpa.mysql.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import stock_microservices.adapters.driven.jpa.mysql.entity.ArticleEntity;
import stock_microservices.adapters.driven.jpa.mysql.entity.CategoriesEntity;
import stock_microservices.domain.model.Article;
import stock_microservices.domain.model.Categories;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ArticleEntityMapper {

    @Mapping(source = "brand.id", target = "brand.id")
    @Mapping(source = "brand.name", target = "brand.name")
    @Mapping(source = "brand.description", target = "brand.description")
    @Mapping(target = "categories", source = "categories", qualifiedByName = "toCategoryEntitySet")
    ArticleEntity toEntity(Article article);

    @Mapping(source = "brand.id", target = "brand.id")
    @Mapping(source = "brand.name", target = "brand.name")
    @Mapping(source = "brand.description", target = "brand.description")
    @Mapping(target = "categories", source = "categories", qualifiedByName = "toCategoryDomainSet")
    Article toModel(ArticleEntity articleEntity);

    @Named("toCategoryEntitySet")
    @IterableMapping(elementTargetType = CategoriesEntity.class)
    default Set<CategoriesEntity> toCategoryEntitySet(Set<Categories> categorySet) {
        return categorySet.stream()
                .map(category -> new CategoriesEntity(
                        category.getId(),
                        category.getName(),
                        null,
                        null
                ))
                .collect(Collectors.toSet());
    }

    @IterableMapping(elementTargetType = Categories.class)
    @Named("toCategoryDomainSet")
    default Set<Categories> toCategoryDomainSet(Set<CategoriesEntity> categoriesEntities) {
        return categoriesEntities.stream()
                .map(categoryEntity -> new Categories(
                        categoryEntity.getId(),
                        categoryEntity.getName(),
                        null
                ))
                .collect(Collectors.toSet());
    }
}