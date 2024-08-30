package stock_microservices.adapters.driving.http.controller;

import org.springframework.data.domain.*;
import stock_microservices.adapters.driving.http.dto.request.AddArticleRequest;
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
    public ResponseEntity<Article> createArticle(@RequestBody AddArticleRequest request) {
        Article article = articleRequestMapper.toArticle(request);
        articleServicePort.createArticle(article);
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<ArticleResponse> getArticleByName(@PathVariable String name) {
        Article article = articleServicePort.getArticleByName(name);
        return ResponseEntity.ok(articleResponseMapper.toArticleResponse(article));
        }

    @GetMapping("/")
    public ResponseEntity<Page<ArticleResponse>> getAllArticles(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size,
                                                                @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, direction, "name");
        Page<Article> articlesPage = articleServicePort.getAllArticles(pageable);
        List<ArticleResponse> responseList = articleResponseMapper.toArticleResponseList(articlesPage.getContent());
        Page<ArticleResponse> responsePage = new PageImpl<>(responseList, pageable, articlesPage.getTotalElements());
        return ResponseEntity.ok(responsePage);
    }
}

