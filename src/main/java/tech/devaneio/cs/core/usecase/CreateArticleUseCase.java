package tech.devaneio.cs.core.usecase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;
import tech.devaneio.cs.core.entity.Article;

import static tech.devaneio.cs.core.entity.ArticleStatus.DRAFT;

@Validated
public interface CreateArticleUseCase {

    Output execute(@Valid @NotNull Input input);

    @Builder
    record Input(@NotBlank String title,
                 @NotNull String description,
                 @NotNull  String content) {

        public Article article() {
            return Article.builder()
                .title(title())
                .description(description())
                .content(content())
                .status(DRAFT)
                .build();
        }

    }

    record Output(Article article) {
    }

}
