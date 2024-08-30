package stock_microservices.adapters.driven.jpa.mysql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import stock_microservices.adapters.driven.jpa.mysql.entity.BrandEntity;
import stock_microservices.domain.model.Brand;

@Mapper(componentModel = "spring")
public interface BrandEntityMapper {

    @Mapping(source = "id", target = "id")
    BrandEntity toEntity(Brand brand);
    Brand toModel(BrandEntity brandEntity);
}

