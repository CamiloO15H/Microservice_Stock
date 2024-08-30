package stock_microservices.adapters.driving.http.dto.request;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.Set;

@AllArgsConstructor
@Data
public class AddArticleRequest {
    private final String name;
    private final String description;
    private final Integer amount;
    private final Double price;
    private final Long brandId; // id_brand// ids for the categories
    private final Set<Long> categoriesId; // id_category // ids for the suppliers
}
