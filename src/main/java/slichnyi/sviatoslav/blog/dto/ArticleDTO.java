package slichnyi.sviatoslav.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleDTO {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotBlank
    private String authorUsername;

    @NotBlank
    private String categoryName;

    @NotEmpty
    private List<@NotBlank String> tagNames;
}