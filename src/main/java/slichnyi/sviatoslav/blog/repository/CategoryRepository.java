package slichnyi.sviatoslav.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import slichnyi.sviatoslav.blog.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}

