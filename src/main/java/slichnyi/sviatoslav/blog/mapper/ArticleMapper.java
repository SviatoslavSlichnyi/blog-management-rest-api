package slichnyi.sviatoslav.blog.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import slichnyi.sviatoslav.blog.dto.ArticleDTO;
import slichnyi.sviatoslav.blog.entity.Article;
import slichnyi.sviatoslav.blog.entity.Tag;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArticleMapper {

    @Mapping(target = "authorUsername", source = "author.username")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "tagNames", source = "tags")
    ArticleDTO toDto(Article article);

    default List<String> mapTags(List<Tag> tags) {
        return tags.stream().map(Tag::getName).toList();
    }
}