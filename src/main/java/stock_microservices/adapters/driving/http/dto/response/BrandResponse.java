package stock_microservices.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BrandResponse {
    private Long id;
    private String name;
    private String description;
}
