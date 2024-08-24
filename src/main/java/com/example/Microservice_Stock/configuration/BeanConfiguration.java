package com.example.Microservice_Stock.configuration;

import com.example.Microservice_Stock.adapters.driven.jpa.mysql.adapter.CategoriesAdapter;
import com.example.Microservice_Stock.adapters.driven.jpa.mysql.mapper.ICategoriesEntityMapper;
import com.example.Microservice_Stock.adapters.driven.jpa.mysql.repository.ICategoriesRepository;
import com.example.Microservice_Stock.domain.api.ICategoriesServicePort;
import com.example.Microservice_Stock.domain.api.usecase.CategoriesUseCase;
import com.example.Microservice_Stock.domain.spi.ICategoriesPersistencePort;
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
