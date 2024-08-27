package stock_microservices.adapters.driving.http.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import stock_microservices.adapters.driving.http.dto.request.AddBrandRequest;
import stock_microservices.adapters.driving.http.dto.request.UpdateBrandRequest;
import stock_microservices.domain.model.Brand;

@Mapper(componentModel = "spring")
public interface BrandRequestMapper {

    @Mapping(target = "id", ignore = true)
    Brand addRequestToBrand(AddBrandRequest addBrandRequest);
    Brand updateRequestToBrand(UpdateBrandRequest updateBrandRequest);
}
