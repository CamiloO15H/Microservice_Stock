package stock_microservices.adapters.driving.http.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddCategoriesRequest {
    private final String name;
    private final String description;
}

//Aca lo que debemos traer, no necesitamos el id
