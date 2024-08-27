package stock_microservices.adapters.driving.http.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddBrandRequest {
    private final String name;
    private final String description;
}
