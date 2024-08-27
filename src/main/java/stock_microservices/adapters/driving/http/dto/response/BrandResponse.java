package stock_microservices.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BrandResponse {
    private Long id;
    private String name;
    private String description;
}
