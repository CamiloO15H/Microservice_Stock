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

    //implementacion de los metodos de la interfaz IBrandServicePort

    @Override
    public void createBrand(Brand brand) {brandPersistencePort.createBrand(brand);}

    @Override
    public List<Brand> getAllBrand(Integer page, Integer size) {
        return brandPersistencePort.getAllBrand(page, size);
    }

    @Override
    public Brand getBrandByName(String name) {
        return brandPersistencePort.getBrandByName(name);
    }

    @Override
    public Brand updateBrand(Brand brand) {return brandPersistencePort.updateBrand(brand);}

    @Override
    public void deleteBrand(Long id) {
        brandPersistencePort.deleteBrand(id);
    }
}
