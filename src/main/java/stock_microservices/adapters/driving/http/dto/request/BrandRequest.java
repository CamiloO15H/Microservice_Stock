package stock_microservices.adapters.driving.http.dto.request;

import stock_microservices.domain.utils.DomainConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandRequest {
    //Attributes
    @NotNull(message = DomainConstants.FIELD_NAME_NULL_MESSAGE)
    @Size(max=50, message = "Name must be shorter than 50 chars")
    private String name;

    @NotNull(message = DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
    @Size(max=120, message = "Description must be shorter than 120 chars")
    private String description;
}
