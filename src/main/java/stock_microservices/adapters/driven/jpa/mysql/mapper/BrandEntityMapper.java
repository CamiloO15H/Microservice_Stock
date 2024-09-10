package stock_microservices.adapters.driven.jpa.mysql.mapper;

import org.mapstruct.*;
import org.springframework.data.domain.Page;
import stock_microservices.adapters.driven.jpa.mysql.entity.ProductEntity;
import stock_microservices.adapters.driven.jpa.mysql.entity.BrandEntity;
import stock_microservices.domain.model.Brand;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.domain.model.Product;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandEntityMapper {
    BrandEntity toEntity(Brand brand);

    Brand toBrand(BrandEntity brandEntity);

    @Named("brandWithoutProducts")
    @Mapping(target = "products", ignore = true)
    Brand withoutProducts(BrandEntity source);

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "brand", ignore = true)
    Product toProduct(ProductEntity productEntity);

    @IterableMapping(qualifiedByName = "brandWithoutProducts")
    List<Brand> toBrands(List<BrandEntity> brandEntities);

    @Mapping(target = "page", source = "number")
    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "count", source = "numberOfElements")
    @Mapping(target = "totalCount", source = "totalElements")
    @Mapping(target = "content", source = "content")
    @IterableMapping(qualifiedByName = "brandWithoutProducts")
    DomainPage<Brand> toDomainPage(Page<BrandEntity> brandEntities);
}
