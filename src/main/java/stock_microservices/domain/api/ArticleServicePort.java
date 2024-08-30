package stock_microservices.domain.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stock_microservices.domain.model.Article;

public interface ArticleServicePort {

    void createArticle(Article article);
    Page<Article> getAllArticles(Pageable pageable);
    Article getArticleByName(String name);
}
