package tech.devaneio.cs.core.usecase.impl;

import lombok.RequiredArgsConstructor;
import tech.devaneio.cs.core.entity.Article;
import tech.devaneio.cs.core.mapping.UseCase;
import tech.devaneio.cs.core.service.ArticleService;
import tech.devaneio.cs.core.usecase.CreateArticleUseCase;

@UseCase
@RequiredArgsConstructor
public class CreateArticleUseCaseImpl implements CreateArticleUseCase {

    private final ArticleService articleService;

    @Override
    public Output execute(final Input input) {
        final var article = persist(input.article());
        return new Output(article);
    }

    private Article persist(final Article article) {
        return articleService.save(article);
    }

}
