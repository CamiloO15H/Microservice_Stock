package stock_microservice.adapters.driven.jpa.mysql.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stock_microservice.adapters.driven.jpa.mysql.entity.BrandEntity;

import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
    Optional<BrandEntity> findByNameContaining(String name);
    Optional<BrandEntity> findByName(String name);
    Page<BrandEntity> findAll(Pageable pageable);
}
