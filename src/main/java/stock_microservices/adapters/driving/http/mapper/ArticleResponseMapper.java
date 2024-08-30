package stock_microservices.adapters.driving.http.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import stock_microservices.adapters.driving.http.dto.response.ArticleResponse;
import stock_microservices.domain.model.Article;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleResponseMapper {
    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "brand.name", target = "brand")
    ArticleResponse toArticleResponse(Article article);
    List<ArticleResponse> toArticleResponseList(List<Article> article);
}
