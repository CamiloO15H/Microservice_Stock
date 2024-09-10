package stock_microservices.adapters.driving.http.dto.response;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryResponse {
    //Attributes
    private Long id;
    private String name;
}
