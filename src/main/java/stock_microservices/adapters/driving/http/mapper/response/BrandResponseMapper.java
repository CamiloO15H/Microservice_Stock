package stock_microservices.adapters.driving.http.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import stock_microservices.adapters.driving.http.dto.response.BrandResponse;
import stock_microservices.adapters.driving.http.dto.response.PageResponse;
import stock_microservices.domain.model.Brand;
import stock_microservices.domain.utils.pagination.DomainPage;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandResponseMapper {

    BrandResponse toResponse(Brand brand);

    List<BrandResponse> toResponses(List<Brand> brands);

    PageResponse<BrandResponse> toResponsePage(DomainPage<Brand> brands);
}
