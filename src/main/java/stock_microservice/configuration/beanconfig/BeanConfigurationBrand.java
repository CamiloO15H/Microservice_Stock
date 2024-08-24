package stock_microservice.configuration.beanconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stock_microservice.adapters.driven.jpa.mysql.adapter.BrandAdapter;
import stock_microservice.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import stock_microservice.adapters.driven.jpa.mysql.repository.IBrandRepository;
import stock_microservice.domain.api.IBrandServicePort;
import stock_microservice.domain.api.usecase.BrandUseCase;
import stock_microservice.domain.spi.IBrandPersistencePort;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationBrand {
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Bean
    public IBrandPersistencePort brandPersistencePort() {
        return new BrandAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IBrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort());
    }
}
