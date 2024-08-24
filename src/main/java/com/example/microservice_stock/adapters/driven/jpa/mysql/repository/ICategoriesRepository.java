package com.example.microservice_stock.adapters.driven.jpa.mysql.repository;
import com.example.microservice_stock.adapters.driven.jpa.mysql.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICategoriesRepository extends JpaRepository<CategoriesEntity, Long> {
    Optional<CategoriesEntity> findByNameContaining(String name);
    Optional<CategoriesEntity> findByName(String name);
    Page<CategoriesEntity> findAll(Pageable pageable);
}
