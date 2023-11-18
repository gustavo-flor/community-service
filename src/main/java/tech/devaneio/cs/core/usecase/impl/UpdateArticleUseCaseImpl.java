package tech.devaneio.cs.core.usecase.impl;

import lombok.RequiredArgsConstructor;
import tech.devaneio.cs.core.exception.ArticleNotFoundException;
import tech.devaneio.cs.core.mapping.UseCase;
import tech.devaneio.cs.core.service.ArticleService;
import tech.devaneio.cs.core.usecase.UpdateArticleUseCase;

@UseCase
@RequiredArgsConstructor
public class UpdateArticleUseCaseImpl implements UpdateArticleUseCase {

    private final ArticleService articleService;

    @Override
    public Output execute(final Input input) {
        final var article = articleService.findById(input.id())
            .orElseThrow(ArticleNotFoundException::new);
        article.merge(input.article());
        articleService.save(article);
        return new Output(article);
    }

}
