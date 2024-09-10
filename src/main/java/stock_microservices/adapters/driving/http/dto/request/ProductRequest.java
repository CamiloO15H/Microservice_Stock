package stock_microservices.adapters.driving.http.dto.request;

import stock_microservices.domain.utils.DomainConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotNull(message = DomainConstants.FIELD_NAME_NULL_MESSAGE)
    private String name;

    @NotNull(message = DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
    private String description;

    @NotNull(message = DomainConstants.FIELD_PRICE_NULL_MESSAGE)
    @PositiveOrZero
    private BigDecimal price;

    @NotNull(message = DomainConstants.FIELD_QUANTITY_NULL_MESSAGE)
    @PositiveOrZero
    private Long quantity;

    @Size(min = 1)
    @NotNull(message = DomainConstants.FIELD_CATEGORIES_NULL_MESSAGE)
    private List<ProductCategoryRequest> categories;

    @NotNull(message = DomainConstants.FIELD_BRAND_NULL_MESSAGE)
    private ProductBrandRequest brand;
}
