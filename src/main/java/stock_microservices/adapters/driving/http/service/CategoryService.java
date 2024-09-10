package stock_microservices.adapters.driving.http.service;


import stock_microservices.adapters.driving.http.dto.request.CategoryRequest;
import stock_microservices.adapters.driving.http.dto.request.PaginationRequest;
import stock_microservices.adapters.driving.http.dto.response.CategoryResponse;
import stock_microservices.adapters.driving.http.dto.response.PageResponse;

public interface CategoryService {
    void save(CategoryRequest categoryRequest);
    CategoryResponse getCategory(Long id);
    PageResponse<CategoryResponse> getAllCategories(PaginationRequest paginationRequest);
}
