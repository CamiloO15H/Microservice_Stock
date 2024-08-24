package com.example.microservice_stock.domain.spi;

import com.example.microservice_stock.domain.model.Brand;
import java.util.List;

public interface IBrandPersistencePort {
    void createBrand(Brand brand);

    List<Brand> getAllBrand(Integer page, Integer size);

    Brand getBrandByName(String name);

    Brand updateBrand(Brand brand);

    void deleteBrand(Long id);
}
