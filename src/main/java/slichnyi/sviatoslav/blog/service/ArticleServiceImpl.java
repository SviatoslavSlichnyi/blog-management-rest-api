package slichnyi.sviatoslav.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import slichnyi.sviatoslav.blog.dto.ArticleDTO;
import slichnyi.sviatoslav.blog.entity.ApplicationUser;
import slichnyi.sviatoslav.blog.entity.Article;
import slichnyi.sviatoslav.blog.entity.Category;
import slichnyi.sviatoslav.blog.entity.Tag;
import slichnyi.sviatoslav.blog.exceptoin.NotFoundException;
import slichnyi.sviatoslav.blog.mapper.ArticleMapper;
import slichnyi.sviatoslav.blog.repository.ApplicationUserRepository;
import slichnyi.sviatoslav.blog.repository.ArticleRepository;
import slichnyi.sviatoslav.blog.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final ApplicationUserRepository applicationUserRepository;

    private final CategoryRepository categoryRepository;

    private final ArticleMapper articleMapper;

    @Override
    public List<ArticleDTO> getAllArticles() {
        return articleRepository.findAll().stream().map(articleMapper::toDto).toList();
    }

    @Override
    public ArticleDTO getArticleById(Long id) {
        return articleRepository.findById(id)
                .map(articleMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Article not found with id " + id));
    }

    @Override
    @Transactional
    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        String authorUsername = articleDTO.getAuthorUsername();
        String categoryName = articleDTO.getCategoryName();
        List<Tag> tags = articleDTO.getTagNames().stream().map(name -> Tag.builder().name(name).build()).toList();

        ApplicationUser user = applicationUserRepository.findByUsername(authorUsername)
                .orElseThrow(() -> new NotFoundException("Author not found with name " + authorUsername));
        Category category = categoryRepository.findById(categoryName)
                .orElseThrow(() -> new NotFoundException("Category not found with name " + categoryName));

        Article article = Article.builder()
                .title(articleDTO.getTitle())
                .content(articleDTO.getContent())
                .author(user)
                .category(category)
                .tags(tags)
                .build();

        Article saved = articleRepository.save(article);

        return articleMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ArticleDTO updateArticle(Long id, ArticleDTO articleDTO) {
        String categoryName = articleDTO.getCategoryName();
        List<Tag> tags = articleDTO.getTagNames().stream().map(name -> Tag.builder().name(name).build()).toList();

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Article not found with id " + id));

        Category category = categoryRepository.findById(categoryName)
                .orElseThrow(() -> new NotFoundException("Category not found with name " + categoryName));

        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setCategory(category);

        article.getTags().clear();
        article.getTags().addAll(tags);

        articleRepository.save(article);

        return articleMapper.toDto(article);
    }

    @Override
    @Transactional
    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new NotFoundException("Article not found with id " + id);
        }

        articleRepository.deleteById(id);
    }

    @Override
    public List<ArticleDTO> search(LocalDateTime dateBefore) {
        return articleRepository.findByCreatedAtBefore(dateBefore).stream()
                .map(articleMapper::toDto)
                .toList();
    }
}
