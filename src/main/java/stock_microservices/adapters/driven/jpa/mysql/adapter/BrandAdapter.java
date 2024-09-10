package stock_microservices.adapters.driven.jpa.mysql.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import stock_microservices.adapters.driven.jpa.mysql.entity.BrandEntity;
import stock_microservices.adapters.driven.jpa.mysql.mapper.BrandEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.mapper.PaginationJPAMapper;
import stock_microservices.adapters.driven.jpa.mysql.repository.BrandRepository;
import stock_microservices.domain.exceptions.EntityNotFoundException;
import stock_microservices.domain.model.Brand;
import stock_microservices.domain.spi.BrandPersistencePort;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.domain.utils.pagination.PaginationData;

@Component
@RequiredArgsConstructor
public class BrandAdapter implements BrandPersistencePort {

    private final BrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;
    private final PaginationJPAMapper paginationJPAMapper;

    @Override
    public void save(Brand brand) {
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public Brand getBrand(Long id) {
        return brandEntityMapper.toBrand(brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Brand with id " + id + " not found")));
    }

    @Override
    public Brand getBrandByName(String name) {
        return brandEntityMapper.toBrand(brandRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Brand with name " + name + " not found")));
    }

    @Override
    public DomainPage<Brand> getAllBrands(PaginationData paginationData) {
        Pageable pageable = paginationJPAMapper.toJPA(paginationData).createPageable();
        Page<BrandEntity> returnBrands = brandRepository.findAll(pageable);
        return brandEntityMapper.toDomainPage(returnBrands);
    }
}
