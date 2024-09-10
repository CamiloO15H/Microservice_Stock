package stock_microservices.adapters.driving.http.dto.request;

import stock_microservices.domain.utils.DomainConstants;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductBrandRequest {
    @NotNull(message = DomainConstants.FIELD_ID_NULL_MESSAGE)
    private Long id;
}
