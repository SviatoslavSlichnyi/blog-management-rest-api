package slichnyi.sviatoslav.blog.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import slichnyi.sviatoslav.blog.dto.ArticleDTO;
import slichnyi.sviatoslav.blog.service.ArticleService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public List<ArticleDTO> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/search")
    public List<ArticleDTO> searchArticles(@RequestParam LocalDateTime dateBefore) {
        return articleService.search(dateBefore);
    }

    @GetMapping("/{id}")
    public ArticleDTO getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDTO createArticle(@Valid @RequestBody ArticleDTO articleDTO) {
        return articleService.createArticle(articleDTO);
    }

    @PutMapping("/{id}")
    public ArticleDTO updateArticle(@NotNull @PathVariable Long id, @Valid @RequestBody ArticleDTO articleDTO) {
        return articleService.updateArticle(id, articleDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@NotNull @PathVariable Long id) {
        articleService.deleteArticle(id);
    }
}
