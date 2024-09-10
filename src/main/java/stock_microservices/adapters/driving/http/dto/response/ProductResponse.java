package stock_microservices.adapters.driving.http.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private List<String> categories;
    private String brand;
}
