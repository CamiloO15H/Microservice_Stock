package stock_microservices.domain.spi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stock_microservices.domain.model.Article;
import java.util.List;

public interface ArticlePersistencePort {

    void createArticle(Article article);
    Page<Article> getAllArticles(Pageable pageable);
    Article getArticleByName(String name);
}
