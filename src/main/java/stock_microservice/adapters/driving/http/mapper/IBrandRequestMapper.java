package stock_microservice.adapters.driving.http.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import stock_microservice.adapters.driving.http.dto.request.AddBrandRequest;
import stock_microservice.adapters.driving.http.dto.request.UpdateBrandRequest;
import stock_microservice.domain.model.Brand;

@Mapper(componentModel = "spring")
public interface IBrandRequestMapper {

    @Mapping(target = "id", ignore = true)
    Brand addRequestToBrand(AddBrandRequest addBrandRequest);
    Brand updateRequestToBrand(UpdateBrandRequest updateBrandRequest);
}
