package stock_microservices.adapters.driving.http.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stock_microservices.adapters.driving.http.dto.request.PaginationRequest;
import stock_microservices.adapters.driving.http.dto.request.ProductRequest;
import stock_microservices.adapters.driving.http.dto.response.PageResponse;
import stock_microservices.adapters.driving.http.dto.response.ProductCategoryResponse;
import stock_microservices.adapters.driving.http.dto.response.ProductResponse;
import stock_microservices.adapters.driving.http.mapper.request.PaginationRequestMapper;
import stock_microservices.adapters.driving.http.mapper.request.ProductRequestMapper;
import stock_microservices.adapters.driving.http.mapper.response.ProductResponseMapper;
import stock_microservices.adapters.driving.http.service.ProductService;
import stock_microservices.domain.api.ProductServicePort;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductServicePort productServicePort;
    private final ProductRequestMapper productRequestMapper;
    private final PaginationRequestMapper paginationRequestMapper;
    private final ProductResponseMapper productResponseMapper;


    @Override
    public void save(ProductRequest product) {
        productServicePort.save(productRequestMapper.toProduct(product));
    }

    @Override
    public PageResponse<ProductResponse> getAllProducts(PaginationRequest paginationRequest) {
        return productResponseMapper.toResponsePage(
                productServicePort.getAllProducts(
                        paginationRequestMapper.toPaginationData(paginationRequest)
                )
        );
    }

    @Override
    public List<ProductCategoryResponse> getProductCategories(Long id) {
        return productResponseMapper.toProductCategoryResponses(
                productServicePort.getProductCategories(id)
        );
    }
}
