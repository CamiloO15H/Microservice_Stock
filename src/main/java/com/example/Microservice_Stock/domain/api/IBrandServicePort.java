package com.example.Microservice_Stock.domain.api;
import com.example.Microservice_Stock.domain.model.Brand;
import java.util.List;

public interface IBrandServicePort {
    Void saveBrand(Brand brand);

    List<Brand> getAllBrand();

    Brand getBrand(String name);

    Void updateBrand(Brand brand);

    Void deleteBrand(String name);
}
