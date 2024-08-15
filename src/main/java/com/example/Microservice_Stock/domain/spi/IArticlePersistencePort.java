package com.example.Microservice_Stock.domain.spi;
import com.example.Microservice_Stock.domain.model.Article;
import java.util.List;

public interface IArticlePersistencePort {
    Void saveArticle(Article article);

    List<Article> getAllArticles();

    Article getArticleById(Long name);

    Void updateArticle(Article article);

    Void deleteArticle(Long id);
}
