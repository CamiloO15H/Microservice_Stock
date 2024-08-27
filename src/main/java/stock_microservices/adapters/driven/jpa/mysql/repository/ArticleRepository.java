package stock_microservices.adapters.driven.jpa.mysql.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import stock_microservices.adapters.driven.jpa.mysql.entity.ArticleEntity;

import java.util.Optional;

    //El repositorio JPA proporciona una interfaz para acceder y manipular la base de datos.

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Optional<ArticleEntity> findByNameContaining(String name);
    Optional<ArticleEntity> findByName(String name);
    Page<ArticleEntity> findAll(Pageable pageable);
}