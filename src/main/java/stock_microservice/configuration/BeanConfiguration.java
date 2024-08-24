package stock_microservice.configuration;

import stock_microservice.adapters.driven.jpa.mysql.adapter.CategoriesAdapter;
import stock_microservice.adapters.driven.jpa.mysql.mapper.ICategoriesEntityMapper;
import stock_microservice.adapters.driven.jpa.mysql.repository.ICategoriesRepository;
import stock_microservice.domain.api.ICategoriesServicePort;
import stock_microservice.domain.api.usecase.CategoriesUseCase;
import stock_microservice.domain.spi.ICategoriesPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICategoriesRepository categoriesRepository;
    private final ICategoriesEntityMapper categoriesEntityMapper;

    @Bean
    public ICategoriesPersistencePort categoryPersistencePort() {
        return new CategoriesAdapter(categoriesRepository, categoriesEntityMapper);
    }

    @Bean
    public ICategoriesServicePort categoriesServicePort() {
        return new CategoriesUseCase(categoryPersistencePort());
    }
}
