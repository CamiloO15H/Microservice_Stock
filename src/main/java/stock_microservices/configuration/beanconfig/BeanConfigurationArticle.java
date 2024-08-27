package stock_microservices.configuration.beanconfig;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stock_microservices.adapters.driven.jpa.mysql.adapter.ArticleAdapter;
import stock_microservices.adapters.driven.jpa.mysql.mapper.ArticleEntityMapper;
import stock_microservices.adapters.driven.jpa.mysql.repository.ArticleRepository;
import stock_microservices.domain.api.ArticleServicePort;
import stock_microservices.domain.api.usecase.ArticleUseCase;
import stock_microservices.domain.spi.ArticlePersistencePort;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationArticle {
    private final ArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Bean
    public ArticlePersistencePort articlePersistencePort()  {return new ArticleAdapter(articleRepository, articleEntityMapper); }

    @Bean
    public ArticleServicePort articleServicePort() { return new ArticleUseCase(articlePersistencePort()); }
}
