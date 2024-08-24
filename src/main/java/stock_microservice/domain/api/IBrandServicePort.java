package stock_microservice.domain.api;

import stock_microservice.domain.model.Brand;
import java.util.List;

public interface IBrandServicePort {

    void createBrand(Brand brand);

    List<Brand> getAllBrand(Integer page, Integer size,String sortDirection);

    Brand getBrandByName(String name);

    Brand updateBrand(Brand brand);

    void deleteBrand(Long id);
}
