package stock_microservices.configuration.beanconfig;

import stock_microservices.adapters.driven.jpa.mysql.adapter.CategoriesAdapter;
import stock_microservices.adapters.driven.jpa.mysql.mapper.CategoriesEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.repository.CategoriesRepository;
import stock_microservices.domain.api.CategoriesServicePort;
import stock_microservices.domain.api.usecase.CategoriesUseCase;
import stock_microservices.domain.spi.CategoriesPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationCategories {
    private final CategoriesRepository categoriesRepository;
    private final CategoriesEntityMapper categoriesEntityMapper;

    @Bean
    public CategoriesPersistencePort categoryPersistencePort() {
        return new CategoriesAdapter(categoriesRepository, categoriesEntityMapper);
    }

    @Bean
    public CategoriesServicePort categoriesServicePort() {
        return new CategoriesUseCase(categoryPersistencePort());
    }
}
