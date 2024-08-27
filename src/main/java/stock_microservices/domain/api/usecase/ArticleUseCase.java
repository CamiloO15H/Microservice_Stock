package stock_microservices.domain.api.usecase;

import stock_microservices.domain.api.ArticleServicePort;
import stock_microservices.domain.model.Article;
import stock_microservices.domain.spi.ArticlePersistencePort;
import java.util.List;


public class ArticleUseCase implements ArticleServicePort {

    private final ArticlePersistencePort articlePersistencePort;

    public ArticleUseCase(ArticlePersistencePort articlePersistencePort) {this.articlePersistencePort = articlePersistencePort;}

    @Override
    public void createArticle(Article article) {articlePersistencePort.createArticle(article);}

    @Override
    public List<Article> getAllArticles(Integer page,
                                        Integer size,
                                        String sortDirection) {
        return articlePersistencePort.getAllArticles(page, size, sortDirection);}

    @Override
    public Article getArticleByName(String name) {return articlePersistencePort.getArticleByName(name);}

    @Override
    public Article updateArticle(Article article) {return articlePersistencePort.updateArticle(article);}

    @Override
    public void deleteArticle(Long id) {articlePersistencePort.deleteArticle(id);}
}