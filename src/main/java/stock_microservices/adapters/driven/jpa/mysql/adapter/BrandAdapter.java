package stock_microservices.adapters.driven.jpa.mysql.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import stock_microservices.adapters.driven.jpa.mysql.entity.BrandEntity;
import stock_microservices.adapters.driven.jpa.mysql.exception.BrandAlreadyExistsException;
import stock_microservices.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import stock_microservices.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import stock_microservices.adapters.driven.jpa.mysql.mapper.BrandEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.repository.BrandRepository;
import stock_microservices.domain.model.Brand;
import stock_microservices.domain.spi.BrandPersistencePort;

import java.util.List;

@RequiredArgsConstructor
public class BrandAdapter implements BrandPersistencePort {
    private final BrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Override
    public void createBrand(Brand brand) {
        if (brandRepository.findByName(brand.getName()).isPresent()) {
            throw new BrandAlreadyExistsException();
        }
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public List<Brand> getAllBrand(Integer page, Integer size, String sortDirection) {
        // Usa Sort.by para crear el objeto Sort y luego pásalo a PageRequest.of
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), "name");
        Pageable pagination = PageRequest.of(page, size, sort);

        List<BrandEntity> brand = brandRepository.findAll(pagination).getContent();
        if (brand.isEmpty()) {
            throw new NoDataFoundException();
        }
        return brandEntityMapper.toModelList(brand);
    }

    @Override
    public Brand getBrandByName(String name) {
        return brandEntityMapper.toModel(brandRepository.findByNameContaining(name)
                .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    public Brand updateBrand(Brand brand) {
        if (brandRepository.findById(brand.getId()).isEmpty()) {
            throw new ElementNotFoundException();
        }
        return brandEntityMapper.toModel(brandRepository.save(brandEntityMapper.toEntity(brand)));
    }

    @Override
    public void deleteBrand(Long id) {
        if (brandRepository.findById(id).isEmpty()) {
            throw new ElementNotFoundException();
        }
        brandRepository.deleteById(id);
    }
}
