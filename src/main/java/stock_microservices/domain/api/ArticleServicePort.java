package stock_microservices.domain.api;

import stock_microservices.domain.model.Article;
import java.util.List;

public interface ArticleServicePort {

    void createArticle(Article article);

    List<Article> getAllArticles(Integer page, Integer size,String sortDirection);

    Article getArticleByName(String name);

    Article updateArticle(Article article);

    void deleteArticle(Long id);
}
