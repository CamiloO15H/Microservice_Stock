package stock_microservices.configuration.beanconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stock_microservices.adapters.driven.jpa.mysql.adapter.BrandAdapter;
import stock_microservices.adapters.driven.jpa.mysql.mapper.BrandEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.repository.BrandRepository;
import stock_microservices.domain.api.BrandServicePort;
import stock_microservices.domain.api.usecase.BrandUseCase;
import stock_microservices.domain.spi.BrandPersistencePort;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationBrand {
    private final BrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Bean
    public BrandPersistencePort brandPersistencePort() {
        return new BrandAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public BrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort());
    }
}
