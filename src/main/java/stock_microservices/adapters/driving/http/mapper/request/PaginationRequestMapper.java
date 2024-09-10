package stock_microservices.adapters.driving.http.mapper.request;

import stock_microservices.adapters.driving.http.dto.request.PaginationRequest;
import stock_microservices.domain.utils.pagination.PaginationData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaginationRequestMapper {
    PaginationData toPaginationData(PaginationRequest paginationRequest);
}
