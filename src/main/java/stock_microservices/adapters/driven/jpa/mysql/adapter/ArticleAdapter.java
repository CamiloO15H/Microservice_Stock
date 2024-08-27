package stock_microservices.adapters.driven.jpa.mysql.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import stock_microservices.adapters.driven.jpa.mysql.entity.ArticleEntity;
import stock_microservices.adapters.driven.jpa.mysql.mapper.ArticleEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.repository.ArticleRepository;
import stock_microservices.domain.model.Article;
import stock_microservices.domain.spi.ArticlePersistencePort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ArticleAdapter implements ArticlePersistencePort {

    private final ArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public void createArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toEntity(article);
        articleRepository.save(articleEntity);
    }

    @Override
    public Article getArticleByName(String name) {
        return articleRepository.findByName(name)
                .map(articleEntityMapper::toModel)
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    @Override
    public List<Article> getAllArticles(Integer page, Integer size, String sortDirection) {
        return articleRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(articleEntityMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Article updateArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toEntity(article);
        return articleEntityMapper.toModel(articleRepository.save(articleEntity));
    }

    @Override
    public void deleteArticle(Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
        } else {
            throw new RuntimeException("Article not found");
        }
    }
}
