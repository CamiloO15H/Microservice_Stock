package stock_microservices.adapters.driven.jpa.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stock_microservices.adapters.driven.jpa.mysql.entity.ProductEntity;

    //El repositorio JPA proporciona una interfaz para acceder y manipular la base de datos.

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
