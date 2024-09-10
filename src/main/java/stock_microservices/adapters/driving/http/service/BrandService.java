package stock_microservices.adapters.driving.http.service;

import stock_microservices.adapters.driving.http.dto.response.BrandResponse;
import stock_microservices.adapters.driving.http.dto.request.BrandRequest;
import stock_microservices.adapters.driving.http.dto.response.PageResponse;
import stock_microservices.adapters.driving.http.dto.request.PaginationRequest;

public interface BrandService {
    void save(BrandRequest brandRequest);
    PageResponse<BrandResponse> getAllBrands(PaginationRequest paginationRequest);
}
