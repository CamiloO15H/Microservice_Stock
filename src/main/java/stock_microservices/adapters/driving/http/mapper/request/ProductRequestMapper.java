package stock_microservices.adapters.driving.http.mapper.request;

import stock_microservices.adapters.driving.http.dto.request.ProductRequest;
import stock_microservices.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductRequestMapper {
    Product toProduct(ProductRequest productRequest);
}
