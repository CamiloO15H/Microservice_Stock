package stock_microservices.adapters.driving.http.dto.request;

import lombok.Data;

@Data
public class AddBrandRequest {
    private final String name;
    private final String description;
}
