package stock_microservices.domain.spi;
import stock_microservices.domain.model.Article;
import java.util.List;

public interface ArticlePersistencePort {

    void createArticle(Article article);

    List<Article> getAllArticles(Integer page, Integer size, String sortDirection);

    Article getArticleByName(String name);

    Article updateArticle(Article article);

    void deleteArticle(Long id);
}
