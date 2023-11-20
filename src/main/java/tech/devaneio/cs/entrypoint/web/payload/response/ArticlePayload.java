package tech.devaneio.cs.entrypoint.web.payload.response;

import lombok.Builder;
import tech.devaneio.cs.core.entity.Article;
import tech.devaneio.cs.core.entity.ArticleStatus;

import java.time.LocalDateTime;

@Builder
public record ArticlePayload(Long id,
                             String title,
                             String description,
                             String content,
                             ArticleStatus status,
                             Long userId,
                             LocalDateTime publishedAt,
                             LocalDateTime createdAt,
                             LocalDateTime updatedAt) {

    public static ArticlePayload of(final Article article) {
        if (article == null) {
            return null;
        }
        return ArticlePayload.builder()
            .id(article.getId())
            .title(article.getTitle())
            .description(article.getDescription())
            .content(article.getContent())
            .status(article.getStatus())
            .userId(article.getUserId())
            .publishedAt(article.getPublishedAt())
            .createdAt(article.getCreatedAt())
            .updatedAt(article.getUpdatedAt())
            .build();
    }

}
