package stock_microservices.adapters.driven.jpa.mysql.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import stock_microservices.adapters.driven.jpa.mysql.entity.ProductEntity;
import stock_microservices.adapters.driven.jpa.mysql.mapper.CategoryEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.mapper.PaginationJPAMapper;
import stock_microservices.adapters.driven.jpa.mysql.mapper.ProductEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.repository.ProductRepository;
import stock_microservices.domain.exceptions.EntityNotFoundException;
import stock_microservices.domain.model.Category;
import stock_microservices.domain.model.Product;
import stock_microservices.domain.spi.ProductPersistencePort;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.domain.utils.pagination.PaginationData;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductAdapter implements ProductPersistencePort {

    private final ProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;
    private final CategoryEntityMapper categoryEntityMapper;
    private final PaginationJPAMapper paginationJPAMapper;

    @Override
    public void save(Product product) {
        productRepository.save(productEntityMapper.toEntity(product));
    }

    @Override
    public DomainPage<Product> getAllProducts(PaginationData paginationData) {
        Pageable pageable = paginationJPAMapper.toJPA(paginationData).createPageable();
        Page<ProductEntity> returnProducts = productRepository.findAll(pageable);
        return productEntityMapper.toDomainPage(returnProducts);
    }

    @Override
    public List<Category> getProductCategories(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
        return categoryEntityMapper.toCategories(productEntity.getCategories().stream().toList());
    }
}
