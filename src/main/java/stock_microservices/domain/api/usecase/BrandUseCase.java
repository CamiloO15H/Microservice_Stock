package stock_microservices.domain.api.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stock_microservices.domain.api.BrandServicePort;
import stock_microservices.domain.model.Brand;
import stock_microservices.domain.spi.BrandPersistencePort;

@Service
public class BrandUseCase implements BrandServicePort {
    private final BrandPersistencePort brandPersistencePort;

    public BrandUseCase(BrandPersistencePort brandPersistencePort) { //Constructor
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public Brand createBrand(Brand brand){
        validateBrand(brand);
        brandPersistencePort.createBrand(brand);
        return brand;}

    @Override
    public Page<Brand> getAllBrand(Pageable pageable) {
        return brandPersistencePort.getAllBrand(pageable);
    }

    @Override
    public Brand getBrandByName(String name) {
        return brandPersistencePort.getBrandByName(name);
    }

    @Override
    public Brand updateBrand(Brand brand) {
        return brandPersistencePort.updateBrand(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        brandPersistencePort.deleteBrand(id);
    }

    private void validateBrand(Brand brand) {
        if (brand.getName() == null || brand.getName().isBlank() || brand.getName().length() > 50) {
            throw new IllegalArgumentException("Invalid brand name");
        }
        if (brand.getDescription() == null || brand.getDescription().isBlank() || brand.getDescription().length() > 120) {
            throw new IllegalArgumentException("Invalid brand description");
        }
    }
}

