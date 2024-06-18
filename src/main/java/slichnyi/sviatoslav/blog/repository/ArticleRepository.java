package slichnyi.sviatoslav.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import slichnyi.sviatoslav.blog.entity.Article;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByCreatedAtBefore(LocalDateTime date);
}
