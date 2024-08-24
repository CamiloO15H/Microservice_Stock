package stock_microservice.domain.api.usecase;

import stock_microservice.domain.api.IBrandServicePort;
import stock_microservice.domain.model.Brand;
import stock_microservice.domain.spi.IBrandPersistencePort;
import java.util.List;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) { //Constructor
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void createBrand(Brand brand) {brandPersistencePort.createBrand(brand);}

    @Override
    public List<Brand> getAllBrand(Integer page,
                                   Integer size,
                                   String sortDirection) {
        return brandPersistencePort.getAllBrand(page, size, sortDirection);}

    @Override
    public Brand getBrandByName(String name) {return brandPersistencePort.getBrandByName(name);}

    @Override
    public Brand updateBrand(Brand brand) {return brandPersistencePort.updateBrand(brand);}

    @Override
    public void deleteBrand(Long id) {brandPersistencePort.deleteBrand(id);}
}
