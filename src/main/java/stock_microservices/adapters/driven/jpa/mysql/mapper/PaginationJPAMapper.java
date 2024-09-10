package stock_microservices.adapters.driven.jpa.mysql.mapper;

import stock_microservices.adapters.driven.jpa.mysql.utils.PaginationJPA;
import stock_microservices.domain.utils.pagination.PaginationData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaginationJPAMapper {
    PaginationJPA toJPA(PaginationData paginationData);
}
