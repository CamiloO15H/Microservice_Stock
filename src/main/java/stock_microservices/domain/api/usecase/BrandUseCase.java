package stock_microservices.domain.api.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stock_microservices.domain.api.BrandServicePort;
import stock_microservices.domain.exceptions.EntityAlreadyExistsException;
import stock_microservices.domain.exceptions.EntityNotFoundException;
import stock_microservices.domain.model.Brand;
import stock_microservices.domain.spi.BrandPersistencePort;
import stock_microservices.domain.utils.DomainConstants;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.domain.utils.pagination.PaginationData;

import static stock_microservices.domain.utils.ValidationUtils.validateDescription;
import static stock_microservices.domain.utils.ValidationUtils.validateName;

@Service
public class BrandUseCase implements BrandServicePort {

    private final BrandPersistencePort brandPersistencePort;

    public BrandUseCase(BrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void save(Brand brand) {
        validateName(brand.getName(), DomainConstants.NAME_LENGTH_LIMIT);
        validateDescription(brand.getDescription(), DomainConstants.BRAND_DESCRIPTION_LENGTH_LIMIT);

        try {
            brandPersistencePort.getBrandByName(brand.getName());
            throw new EntityAlreadyExistsException(Brand.class.getSimpleName(), brand.getName());
        } catch (EntityNotFoundException e) {
            brandPersistencePort.save(brand);
        }
    }

    @Override
    public DomainPage<Brand> getAllBrands(PaginationData paginationData) {
        return brandPersistencePort.getAllBrands(paginationData);
    }
}

