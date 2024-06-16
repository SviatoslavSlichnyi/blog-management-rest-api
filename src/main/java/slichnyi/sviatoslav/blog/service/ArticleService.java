package slichnyi.sviatoslav.blog.service;

import slichnyi.sviatoslav.blog.dto.ArticleDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleService {

    List<ArticleDTO> getAllArticles();

    ArticleDTO getArticleById(Long id);

    ArticleDTO createArticle(ArticleDTO articleDTO);

    ArticleDTO updateArticle(Long id, ArticleDTO articleDTO);

    void deleteArticle(Long id);

    List<ArticleDTO> search(LocalDateTime dateBefore);
}
