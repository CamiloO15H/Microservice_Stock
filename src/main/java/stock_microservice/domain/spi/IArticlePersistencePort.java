package stock_microservice.domain.spi;
import stock_microservice.domain.model.Article;
import java.util.List;

public interface IArticlePersistencePort {
    Void saveArticle(Article article);

    List<Article> getAllArticles();

    Article getArticleById(Long name);

    Void updateArticle(Article article);

    Void deleteArticle(Long id);
}
