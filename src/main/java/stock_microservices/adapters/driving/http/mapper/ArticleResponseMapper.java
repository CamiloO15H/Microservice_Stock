package stock_microservices.adapters.driving.http.mapper;

import org.mapstruct.Mapper;
import stock_microservices.adapters.driving.http.dto.response.ArticleResponse;
import stock_microservices.domain.model.Article;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleResponseMapper {

    ArticleResponse toArticleResponse(Article article);
    List<ArticleResponse> toArticleResponseList(List<Article> articles);
}

