package stock_microservices.adapters.driving.http.dto.response;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {
    //Attributes
    private Long id;
    private String name;
    private String description;
}
