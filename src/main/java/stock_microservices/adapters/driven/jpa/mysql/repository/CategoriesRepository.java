package stock_microservices.adapters.driven.jpa.mysql.repository;

import stock_microservices.adapters.driven.jpa.mysql.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Long> {
    // Método para buscar categorías que contengan un nombre específico
    Optional<CategoriesEntity> findByNameContaining(String name);

    // Método para obtener todas las categorías con paginación
    Page<CategoriesEntity> findAll(Pageable pageable);
}
