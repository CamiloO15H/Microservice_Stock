package stock_microservices.adapters.driving.http.controller;

import stock_microservices.adapters.driving.http.dto.request.AddArticleRequest;
import stock_microservices.adapters.driving.http.dto.request.UpdateArticleRequest;
import stock_microservices.adapters.driving.http.dto.response.ArticleResponse;
import stock_microservices.adapters.driving.http.mapper.ArticleRequestMapper;
import stock_microservices.adapters.driving.http.mapper.ArticleResponseMapper;
import stock_microservices.domain.api.ArticleServicePort;
import stock_microservices.domain.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleRestControllerAdapter {

    private final ArticleServicePort articleServicePort;
    private final ArticleRequestMapper articleRequestMapper;
    private final ArticleResponseMapper articleResponseMapper;



    @PostMapping("/")
    public ResponseEntity<Void> createArticle(@RequestBody AddArticleRequest request) {
        articleServicePort.createArticle(articleRequestMapper.addRequestToArticle(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<ArticleResponse> getArticleByName(@PathVariable String name) {
        Article article = articleServicePort.getArticleByName(name);
        return ResponseEntity.ok(articleResponseMapper.toArticleResponse(article));
        }

    @GetMapping("/")
    public ResponseEntity<List<ArticleResponse>> getAllArticles(@RequestParam Integer page,
                                                                @RequestParam Integer size,
                                                                @RequestParam String sortDirection) {

        List<Article> articles = articleServicePort.getAllArticles(page, size, sortDirection);
        return ResponseEntity.ok(articleResponseMapper.toArticleResponseList(articles));
    }

    @PutMapping("/")
    public ResponseEntity<ArticleResponse> updateArticle(@RequestBody UpdateArticleRequest request) {
        return ResponseEntity.ok(articleResponseMapper.toArticleResponse(
                articleServicePort.updateArticle(articleRequestMapper.updateRequestToArticle(request))
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleServicePort.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}

