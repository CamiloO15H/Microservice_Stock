package stock_microservices.adapters.driving.http.mapper.response;


import stock_microservices.adapters.driving.http.dto.response.CategoryResponse;
import stock_microservices.adapters.driving.http.dto.response.PageResponse;
import stock_microservices.domain.model.Category;
import stock_microservices.domain.utils.pagination.DomainPage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryResponseMapper {

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponses(List<Category> categories);

    PageResponse<CategoryResponse> toResponsePage(DomainPage<Category> categories);
}
