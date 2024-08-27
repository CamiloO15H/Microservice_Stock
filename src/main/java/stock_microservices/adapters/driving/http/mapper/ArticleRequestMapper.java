package stock_microservices.adapters.driving.http.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import stock_microservices.adapters.driving.http.dto.request.AddArticleRequest;
import stock_microservices.adapters.driving.http.dto.request.UpdateArticleRequest;
import stock_microservices.domain.model.Article;

@Mapper(componentModel = "spring")
public interface ArticleRequestMapper {

    @Mapping(target = "id", ignore = true)
    Article addRequestToArticle(AddArticleRequest addArticleRequest);
    Article updateRequestToArticle(UpdateArticleRequest updateArticleRequest);
}
