package stock_microservices.adapters.driving.http.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stock_microservices.adapters.driving.http.dto.request.BrandRequest;
import stock_microservices.adapters.driving.http.dto.request.PaginationRequest;
import stock_microservices.adapters.driving.http.dto.response.BrandResponse;
import stock_microservices.adapters.driving.http.dto.response.PageResponse;
import stock_microservices.adapters.driving.http.mapper.request.BrandRequestMapper;
import stock_microservices.adapters.driving.http.mapper.request.PaginationRequestMapper;
import stock_microservices.adapters.driving.http.mapper.response.BrandResponseMapper;
import stock_microservices.adapters.driving.http.service.BrandService;
import stock_microservices.domain.api.BrandServicePort;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandServiceImpl implements BrandService {
    private final BrandServicePort brandServicePort;
    private final BrandResponseMapper brandResponseMapper;
    private final BrandRequestMapper brandRequestMapper;
    private final PaginationRequestMapper paginationRequestMapper;

    @Override
    public void save(BrandRequest brandRequest) {
        brandServicePort.save(brandRequestMapper.toBrand(brandRequest));
    }

    @Override
    public PageResponse<BrandResponse> getAllBrands(PaginationRequest paginationRequest) {
        return brandResponseMapper.toResponsePage(brandServicePort.getAllBrands(paginationRequestMapper.toPaginationData(paginationRequest)));
    }
}
