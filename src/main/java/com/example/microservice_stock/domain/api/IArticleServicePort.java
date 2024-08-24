package com.example.microservice_stock.domain.api;
import com.example.microservice_stock.domain.model.Article;
import java.util.List;

public interface IArticleServicePort {
    Void saveArticle(Article article);

    List<Article> getAllArticles();

    Article getArticleById(Long name);

    Void updateArticle(Article article);

    Void deleteArticle(Long id);
}
