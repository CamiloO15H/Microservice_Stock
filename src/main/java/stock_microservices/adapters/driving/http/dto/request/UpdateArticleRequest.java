package stock_microservices.adapters.driving.http.dto.request;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public class UpdateArticleRequest {
    private final String id;
    private final String name;
    private final String description;
    private final Integer amount;
    private final Double price;
    private final Long idBrand; // id_brand
    private final Long idCategorise; // ids for the categories
}

