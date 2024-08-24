package stock_microservice.adapters.driving.http.dto.request;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public class AddBrandRequest {
    private final String name;
    private final String description;
}
