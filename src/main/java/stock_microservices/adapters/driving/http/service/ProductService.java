package stock_microservices.adapters.driving.http.service;

import stock_microservices.adapters.driving.http.dto.request.PaginationRequest;
import stock_microservices.adapters.driving.http.dto.request.ProductRequest;
import stock_microservices.adapters.driving.http.dto.response.PageResponse;
import stock_microservices.adapters.driving.http.dto.response.ProductCategoryResponse;
import stock_microservices.adapters.driving.http.dto.response.ProductResponse;

import java.util.List;


public interface ProductService {
    void save(ProductRequest product);
    PageResponse<ProductResponse> getAllProducts(PaginationRequest paginationRequest);
    List<ProductCategoryResponse> getProductCategories(Long id);
}
