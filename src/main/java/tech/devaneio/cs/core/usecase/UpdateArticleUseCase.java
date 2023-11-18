package tech.devaneio.cs.core.usecase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;
import tech.devaneio.cs.core.entity.Article;
import tech.devaneio.cs.core.exception.ArticleNotFoundException;

@Validated
public interface UpdateArticleUseCase {

    Output execute(@Valid @NotNull Input input) throws ArticleNotFoundException;

    @Builder
    record Input(@NotNull Long id,
                 @NotBlank String title,
                 @NotNull String description,
                 @NotNull String content) {

        public Article article() {
            return Article.builder()
                .title(title())
                .description(description())
                .content(content())
                .build();
        }

    }

    record Output(Article article) {
    }

}
