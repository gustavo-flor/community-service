package tech.devaneio.cs.core.usecase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import tech.devaneio.cs.core.entity.Article;
import tech.devaneio.cs.core.exception.ArticleNotFoundException;

@Validated
public interface PublishArticleUseCase {

    Output execute(@Valid @NotNull Input input) throws ArticleNotFoundException;

    record Input(@NotNull @Positive Long id) {
    }

    record Output(Article article) {
    }

}
