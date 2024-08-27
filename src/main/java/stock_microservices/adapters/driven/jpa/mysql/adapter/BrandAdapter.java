package stock_microservices.adapters.driven.jpa.mysql.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import stock_microservices.adapters.driven.jpa.mysql.entity.BrandEntity;
import stock_microservices.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import stock_microservices.adapters.driven.jpa.mysql.mapper.BrandEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.repository.BrandRepository;
import stock_microservices.domain.model.Brand;
import stock_microservices.domain.spi.BrandPersistencePort;
@Component
public class BrandAdapter implements BrandPersistencePort {
    private final BrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Autowired
    public BrandAdapter(BrandRepository brandRepository, BrandEntityMapper brandEntityMapper) {
        this.brandRepository = brandRepository;
        this.brandEntityMapper = brandEntityMapper;
    }

    @Override
    public void createBrand(Brand brand) {
        BrandEntity entity = brandEntityMapper.toEntity(brand);
        brandRepository.save(entity);
    }

    @Override
    public Page<Brand> getAllBrand(Pageable pageable) {
        Page<BrandEntity> entities = brandRepository.findAll(pageable);
        return entities.map(brandEntityMapper::toModel);
    }

    @Override
    public Brand getBrandByName(String name) {
        return brandEntityMapper.toModel(brandRepository.findByNameContaining(name)
                .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    public Brand updateBrand(Brand brand) {
        BrandEntity entity = brandEntityMapper.toEntity(brand);
        BrandEntity updatedEntity = brandRepository.save(entity);
        return brandEntityMapper.toModel(updatedEntity);
    }

    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}
