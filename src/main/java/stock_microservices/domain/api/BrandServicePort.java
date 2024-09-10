package stock_microservices.domain.api;

import stock_microservices.domain.model.Brand;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.domain.utils.pagination.PaginationData;

public interface BrandServicePort {
    void save(Brand brand);
    DomainPage<Brand> getAllBrands(PaginationData paginationData);
}
