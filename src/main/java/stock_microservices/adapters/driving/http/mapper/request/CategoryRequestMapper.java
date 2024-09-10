package stock_microservices.adapters.driving.http.mapper.request;

import stock_microservices.adapters.driving.http.dto.request.CategoryRequest;
import stock_microservices.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryRequestMapper {
    Category toCategory(CategoryRequest categoryRequest);
}
