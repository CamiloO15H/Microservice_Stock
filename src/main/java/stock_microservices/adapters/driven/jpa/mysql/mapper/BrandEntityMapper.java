package stock_microservices.adapters.driven.jpa.mysql.mapper;

import org.mapstruct.Mapper;
import stock_microservices.adapters.driven.jpa.mysql.entity.BrandEntity;
import stock_microservices.domain.model.Brand;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandEntityMapper {
    Brand toModel(BrandEntity brandEntity);
    BrandEntity toEntity(Brand brand);
    List<Brand> toModelList(List<BrandEntity> brandEntity);
}
