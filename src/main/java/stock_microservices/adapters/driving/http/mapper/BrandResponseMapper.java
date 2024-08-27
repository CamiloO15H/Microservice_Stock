package stock_microservices.adapters.driving.http.mapper;


import org.mapstruct.Mapper;
import stock_microservices.adapters.driving.http.dto.response.BrandResponse;
import stock_microservices.domain.model.Brand;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandResponseMapper {

    BrandResponse toBrandResponse(Brand brand);
    List<BrandResponse> toBrandResponseList(List<Brand> brand);
}
