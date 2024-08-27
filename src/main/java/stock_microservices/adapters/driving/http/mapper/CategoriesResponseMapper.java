package stock_microservices.adapters.driving.http.mapper;

import stock_microservices.adapters.driving.http.dto.response.CategoriesResponse;
import stock_microservices.domain.model.Categories;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriesResponseMapper {
    // Mapea una entidad Categories a un objeto CategoriesResponse
    CategoriesResponse toCategoriesResponse(Categories categories);
    // Mapea una lista de entidades Categories a una lista de objetos CategoriesResponse
    List<CategoriesResponse> toCategoriesResponseList(List<Categories> categories);
}
