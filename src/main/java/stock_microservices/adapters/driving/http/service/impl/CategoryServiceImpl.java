package stock_microservices.adapters.driving.http.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stock_microservices.adapters.driving.http.dto.request.CategoryRequest;
import stock_microservices.adapters.driving.http.dto.request.PaginationRequest;
import stock_microservices.adapters.driving.http.dto.response.CategoryResponse;
import stock_microservices.adapters.driving.http.dto.response.PageResponse;
import stock_microservices.adapters.driving.http.mapper.request.CategoryRequestMapper;
import stock_microservices.adapters.driving.http.mapper.request.PaginationRequestMapper;
import stock_microservices.adapters.driving.http.mapper.response.CategoryResponseMapper;
import stock_microservices.adapters.driving.http.service.CategoryService;
import stock_microservices.domain.api.CategoryServicePort;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryServicePort categoryServicePort;
    private final CategoryResponseMapper categoryResponseMapper;
    private final CategoryRequestMapper categoryRequestMapper;
    private final PaginationRequestMapper paginationRequestMapper;

    @Override
    public void save(CategoryRequest categoryRequest) {
        categoryServicePort.save(categoryRequestMapper.toCategory(categoryRequest));
    }

    @Override
    public CategoryResponse getCategory(Long id) {
        return categoryResponseMapper.toResponse(categoryServicePort.getCategory(id));
    }

    @Override
    public PageResponse<CategoryResponse> getAllCategories(PaginationRequest paginationRequest) {
        return categoryResponseMapper.toResponsePage(
                categoryServicePort.getAllCategories(
                        paginationRequestMapper.toPaginationData(paginationRequest)
                )
        );
    }
}
