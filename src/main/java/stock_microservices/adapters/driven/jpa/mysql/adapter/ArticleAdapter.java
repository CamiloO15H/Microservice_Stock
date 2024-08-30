package stock_microservices.adapters.driven.jpa.mysql.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import stock_microservices.adapters.driven.jpa.mysql.entity.ArticleEntity;
import stock_microservices.adapters.driven.jpa.mysql.mapper.ArticleEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.repository.ArticleRepository;
import stock_microservices.domain.model.Article;
import stock_microservices.domain.spi.ArticlePersistencePort;

@Component
public class ArticleAdapter implements ArticlePersistencePort {
    private final ArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Autowired
    public ArticleAdapter(ArticleRepository articleRepository, ArticleEntityMapper articleEntityMapper) {
        this.articleRepository = articleRepository;
        this.articleEntityMapper = articleEntityMapper;
    }

    @Override
    public void createArticle(Article article) {
        ArticleEntity entity = articleEntityMapper.toEntity(article);
        articleRepository.save(entity);
    }

    @Override
    public Article getArticleByName(String name) {
        return articleRepository.findByName(name)
                .map(articleEntityMapper::toModel)
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    @Override
    public Page<Article> getAllArticles(Pageable pageable) {
        Page<ArticleEntity> entities = articleRepository.findAll(pageable);
        return entities.map(articleEntityMapper::toModel);
    }
}
