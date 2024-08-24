package stock_microservice.adapters.driving.http.mapper;

import stock_microservice.adapters.driving.http.dto.response.CategoriesResponse;
import stock_microservice.domain.model.Categories;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoriesResponseMapper {
    // Mapea una entidad Categories a un objeto CategoriesResponse
    CategoriesResponse toCategoriesResponse(Categories categories);
    // Mapea una lista de entidades Categories a una lista de objetos CategoriesResponse
    List<CategoriesResponse> toCategoriesResponseList(List<Categories> categories);
}
