package stock_microservices.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoriesResponse {
    private Long id;
    private String name;
    private String description;
}

//Lo que le VAMOS A RETORNAR A NUESTRO USUARIO CLIENTE