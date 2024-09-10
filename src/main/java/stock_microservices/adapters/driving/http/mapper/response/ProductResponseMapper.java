package stock_microservices.adapters.driving.http.mapper.response;

import stock_microservices.domain.model.Brand;
import stock_microservices.domain.model.Category;
import stock_microservices.domain.model.Product;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.adapters.driving.http.dto.response.PageResponse;
import stock_microservices.adapters.driving.http.dto.response.ProductCategoryResponse;
import stock_microservices.adapters.driving.http.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductResponseMapper {

    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponses(List<Product> products);

    PageResponse<ProductResponse> toResponsePage(DomainPage<Product> brands);

    List<String> categoryNames(List<Category> categories);

    default String category(Category category){
        return category.getName();
    }

    default String brand(Brand brand){
        return brand.getName();
    }

    List<ProductCategoryResponse> toProductCategoryResponses(List<Category> categories);
}
