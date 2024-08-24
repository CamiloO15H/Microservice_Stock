package stock_microservice.adapters.driving.http.mapper;


import org.mapstruct.Mapper;
import stock_microservice.adapters.driving.http.dto.response.BrandResponse;
import stock_microservice.domain.model.Brand;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {
    BrandResponse toBrandResponse(Brand brand);
    List<BrandResponse> toBrandResponseList(List<Brand> brand);
}
