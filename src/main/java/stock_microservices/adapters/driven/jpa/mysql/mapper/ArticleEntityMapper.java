package stock_microservices.adapters.driven.jpa.mysql.mapper;

import org.mapstruct.Mapper;
import stock_microservices.adapters.driven.jpa.mysql.entity.ArticleEntity;
import stock_microservices.domain.model.Article;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleEntityMapper {

    //El mapper convierte entre la entidad JPA y el modelo de dominio. Usamos MapStruct para este propósito.
    Article toModel(ArticleEntity articleEntity);
    ArticleEntity toEntity(Article article);
    List<Article> toModelList(List<ArticleEntity> articleEntity);
}
