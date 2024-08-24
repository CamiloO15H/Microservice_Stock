package stock_microservice.domain.api;
import stock_microservice.domain.model.Article;
import java.util.List;

public interface IArticleServicePort {
    Void saveArticle(Article article);

    List<Article> getAllArticles();

    Article getArticleById(Long name);

    Void updateArticle(Article article);

    Void deleteArticle(Long id);
}
