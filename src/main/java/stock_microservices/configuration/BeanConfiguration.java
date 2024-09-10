package stock_microservices.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stock_microservices.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import stock_microservices.adapters.driven.jpa.mysql.adapter.ProductAdapter;
import stock_microservices.adapters.driven.jpa.mysql.mapper.BrandEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.mapper.CategoryEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.mapper.PaginationJPAMapper;
import stock_microservices.adapters.driven.jpa.mysql.mapper.ProductEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.repository.BrandRepository;
import stock_microservices.adapters.driven.jpa.mysql.repository.CategoryRepository;
import stock_microservices.adapters.driven.jpa.mysql.repository.ProductRepository;
import stock_microservices.domain.api.CategoryServicePort;
import stock_microservices.domain.api.ProductServicePort;
import stock_microservices.domain.api.usecase.CategoryUseCase;
import stock_microservices.domain.api.usecase.ProductUseCase;
import stock_microservices.domain.spi.BrandPersistencePort;
import stock_microservices.domain.spi.CategoryPersistencePort;
import stock_microservices.domain.spi.ProductPersistencePort;
import stock_microservices.domain.api.usecase.BrandUseCase;
import stock_microservices.adapters.driven.jpa.mysql.adapter.BrandAdapter;
import stock_microservices.domain.api.BrandServicePort;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final BrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;
    private final ProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;
    private final PaginationJPAMapper paginationJPAMapper;

    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper, paginationJPAMapper);
    }

    @Bean
    public CategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public BrandPersistencePort brandPersistencePort() {
        return new BrandAdapter(brandRepository, brandEntityMapper, paginationJPAMapper);
    }

    @Bean
    public BrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort());
    }

    @Bean
    public ProductPersistencePort productPersistencePort() {
        return new ProductAdapter(productRepository, productEntityMapper, categoryEntityMapper, paginationJPAMapper);
    }

    @Bean
    public ProductServicePort productServicePort(){
        return new ProductUseCase(productPersistencePort(), categoryPersistencePort(), brandPersistencePort());
    }

  }
