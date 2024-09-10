package stock_microservices.adapters.driving.http.mapper.request;

import stock_microservices.adapters.driving.http.dto.request.BrandRequest;
import stock_microservices.domain.model.Brand;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandRequestMapper {
    Brand toBrand(BrandRequest brandRequest);
}
