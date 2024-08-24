package com.example.microservice_stock.configuration;

import com.example.microservice_stock.adapters.driven.jpa.mysql.adapter.CategoriesAdapter;
import com.example.microservice_stock.adapters.driven.jpa.mysql.mapper.ICategoriesEntityMapper;
import com.example.microservice_stock.adapters.driven.jpa.mysql.repository.ICategoriesRepository;
import com.example.microservice_stock.domain.api.ICategoriesServicePort;
import com.example.microservice_stock.domain.api.usecase.CategoriesUseCase;
import com.example.microservice_stock.domain.spi.ICategoriesPersistencePort;
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
