package stock_microservices.domain.spi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stock_microservices.domain.model.Brand;
import java.util.List;

public interface BrandPersistencePort {

    void createBrand(Brand brand);

    Page<Brand> getAllBrand(Pageable pageable);

    Brand getBrandByName(String name);

    Brand updateBrand(Brand brand);

    void deleteBrand(Long id);
}
