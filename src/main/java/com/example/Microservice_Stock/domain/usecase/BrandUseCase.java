package com.example.Microservice_Stock.domain.usecase;
import com.example.Microservice_Stock.domain.api.IBrandServicePort;
import com.example.Microservice_Stock.domain.model.Brand;
import com.example.Microservice_Stock.domain.spi.IBrandPersistencePort;
import java.util.List;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) { //Constructor
        this.brandPersistencePort = brandPersistencePort;
    }

    //implementacion de los metodos de la interfaz IBrandServicePort

    @Override
    public Void saveBrand(Brand brand) {
        return brandPersistencePort.saveBrand(brand);
    }

    @Override
    public List<Brand> getAllBrand() {
        return brandPersistencePort.getAllBrand();
    }

    @Override
    public Brand getBrand(String name) {
        return brandPersistencePort.getBrand(name);
    }

    @Override
    public Void updateBrand(Brand brand) {
        return null;
    }

    @Override
    public Void deleteBrand(String name) {
        return null;
    }
}
