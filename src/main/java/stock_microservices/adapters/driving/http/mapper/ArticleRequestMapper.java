package stock_microservices.adapters.driving.http.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import stock_microservices.adapters.driving.http.dto.request.AddArticleRequest;
import stock_microservices.domain.model.Article;

@Mapper(componentModel = "spring")
public interface ArticleRequestMapper {

    @Mapping(target = "id", ignore = true)

    @Mapping(source = "brandId", target = "brand.id")
    @Mapping(target = "brand.name", constant = "name")
    @Mapping(target = "brand.description", constant = "description")

    Article toArticle(AddArticleRequest addArticleRequest);
}
