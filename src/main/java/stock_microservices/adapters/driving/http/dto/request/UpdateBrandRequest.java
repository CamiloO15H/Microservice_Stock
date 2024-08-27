package stock_microservices.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateBrandRequest {
    private Long id;
    private String name;
    private String description;
}
