package stock_microservice.adapters.driven.jpa.mysql.mapper;

import org.mapstruct.Mapper;
import stock_microservice.adapters.driven.jpa.mysql.entity.BrandEntity;
import stock_microservice.domain.model.Brand;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {
    Brand toModel(BrandEntity brandEntity);
    BrandEntity toEntity(Brand brand);
    List<Brand> toModelList(List<BrandEntity> brandEntity);
}
