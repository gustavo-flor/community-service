package tech.devaneio.cs.core.usecase.impl;

import lombok.RequiredArgsConstructor;
import tech.devaneio.cs.core.exception.ArticleNotFoundException;
import tech.devaneio.cs.core.mapping.UseCase;
import tech.devaneio.cs.core.service.ArticleService;
import tech.devaneio.cs.core.usecase.PublishArticleUseCase;

import static java.time.LocalDateTime.now;
import static tech.devaneio.cs.core.entity.ArticleStatus.PUBLISHED;

@UseCase
@RequiredArgsConstructor
public class PublishArticleUseCaseImpl implements PublishArticleUseCase {

    private final ArticleService articleService;

    @Override
    public Output execute(final Input input) {
        final var article = articleService.findById(input.id()).orElseThrow(ArticleNotFoundException::new);
        article.setStatus(PUBLISHED);
        article.setPublishedAt(now());
        articleService.save(article);
        return new Output(article);
    }

}
