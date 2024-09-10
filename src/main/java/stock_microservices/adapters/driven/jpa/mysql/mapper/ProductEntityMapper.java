package stock_microservices.adapters.driven.jpa.mysql.mapper;

import org.mapstruct.*;
import org.springframework.data.domain.Page;
import stock_microservices.adapters.driven.jpa.mysql.entity.CategoryEntity;
import stock_microservices.adapters.driven.jpa.mysql.entity.ProductEntity;
import stock_microservices.adapters.driven.jpa.mysql.entity.BrandEntity;
import stock_microservices.domain.model.Product;
import stock_microservices.domain.model.Brand;
import stock_microservices.domain.model.Category;
import stock_microservices.domain.utils.pagination.DomainPage;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductEntityMapper {
    ProductEntity toEntity(Product product);

    Product toProduct(ProductEntity productEntity);

    @Mapping(target = "products", ignore = true)
    Category toCategory(CategoryEntity categoryEntity);

    @Mapping(target = "products", ignore = true)
    Brand toBrand(BrandEntity brandEntity);

    List<Product> toProducts(List<ProductEntity> productEntities);

    @Mapping(target = "page", source = "number")
    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "count", source = "numberOfElements")
    @Mapping(target = "totalCount", source = "totalElements")
    @Mapping(target = "content", source = "content")
    DomainPage<Product> toDomainPage(Page<ProductEntity> productEntities);
}
