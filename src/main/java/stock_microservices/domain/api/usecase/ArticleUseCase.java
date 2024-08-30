package stock_microservices.domain.api.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stock_microservices.domain.model.Article;
import stock_microservices.domain.model.Categories;
import stock_microservices.domain.spi.ArticlePersistencePort;
import stock_microservices.domain.api.ArticleServicePort;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleUseCase implements ArticleServicePort {
    private final ArticlePersistencePort articlePersistencePort;

    @Autowired
    public ArticleUseCase(ArticlePersistencePort articlePersistencePort) {this.articlePersistencePort = articlePersistencePort;}

    @Override
    public void createArticle(Article article) {
        validateArticle(article);
        articlePersistencePort.createArticle(article);
    }

    @Override
    public Page<Article> getAllArticles(Pageable pageable) {
        return articlePersistencePort.getAllArticles(pageable);
    }

    @Override
    public Article getArticleByName(String name) {
        return articlePersistencePort.getArticleByName(name);
    }

    private void validateArticle(Article article) {
        if (article.getName() == null || article.getName().isBlank()) {
            throw new IllegalArgumentException("Article name cannot be null or blank");
        }
        if (article.getDescription() == null || article.getDescription().isBlank()) {
            throw new IllegalArgumentException("Article description cannot be null or blank");
        }
        if (article.getCategories() == null || article.getCategories().isEmpty()) {
            throw new IllegalArgumentException("Article must have at least one category");
        }
        if (article.getCategories().size() > 3) {
            throw new IllegalArgumentException("Article cannot have more than three categories");
        }
        List<Long> categoryIds = new ArrayList<>();
        for (Categories category : article.getCategories()) {
            if (categoryIds.contains(category.getId())) {
                throw new IllegalArgumentException("Article cannot have duplicate categories.");
            }
            categoryIds.add(category.getId());
        }
    }
}

