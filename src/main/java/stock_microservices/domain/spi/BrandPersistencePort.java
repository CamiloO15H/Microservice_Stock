package stock_microservices.domain.spi;

import stock_microservices.domain.model.Brand;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.domain.utils.pagination.PaginationData;

public interface BrandPersistencePort {
    void save(Brand brand);
    Brand getBrand(Long id);
    Brand getBrandByName(String name);
    DomainPage<Brand> getAllBrands(PaginationData paginationData);
}
