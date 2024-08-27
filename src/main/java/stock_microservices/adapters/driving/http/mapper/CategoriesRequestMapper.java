package stock_microservices.adapters.driving.http.mapper;

import stock_microservices.adapters.driving.http.dto.request.AddCategoriesRequest;
import stock_microservices.adapters.driving.http.dto.request.UpdateCategoriesRequest;
import stock_microservices.domain.model.Categories;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoriesRequestMapper {

    // Mapea un objeto AddCategoriesRequest a una entidad Categories
    @Mapping(target = "id", ignore = true)  // Ignora el campo 'id' porque es autogenerado
    Categories addRequestToCategories(AddCategoriesRequest addCategoriesRequest);
    Categories updateRequestToCategories(UpdateCategoriesRequest updateCategoriesRequest);
}
