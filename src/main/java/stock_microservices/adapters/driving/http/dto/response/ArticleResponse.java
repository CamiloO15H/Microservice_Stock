package stock_microservices.adapters.driving.http.dto.response;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@Data
public class ArticleResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final Integer amount;
    private final Double price;
    private final Long brandId; // id_brand
    private final String brand;
    private Set<CategoriesResponse> categories; // Lista de categorías con id y nombre
}
