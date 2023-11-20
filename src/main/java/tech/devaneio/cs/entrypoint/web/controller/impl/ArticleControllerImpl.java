package tech.devaneio.cs.entrypoint.web.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import tech.devaneio.cs.core.service.ArticleService;
import tech.devaneio.cs.core.usecase.CreateArticleUseCase;
import tech.devaneio.cs.core.usecase.UpdateArticleUseCase;
import tech.devaneio.cs.entrypoint.web.controller.ArticleController;
import tech.devaneio.cs.entrypoint.web.payload.request.CreateArticlePayload;
import tech.devaneio.cs.entrypoint.web.payload.request.UpdateArticlePayload;
import tech.devaneio.cs.entrypoint.web.payload.response.ArticlePayload;
import tech.devaneio.cs.entrypoint.web.query.ArticleSearchQuery;

@RestController
@RequiredArgsConstructor
public class ArticleControllerImpl implements ArticleController {

    private final ArticleService articleService;
    private final CreateArticleUseCase createArticleUseCase;
    private final UpdateArticleUseCase updateArticleUseCase;

    @Override
    public Page<ArticlePayload> findAll(final ArticleSearchQuery query) {
        return articleService.findAll(query.search()).map(ArticlePayload::of);
    }

    @Override
    public ArticlePayload create(final CreateArticlePayload payload) {
        final var input = payload.input();
        final var output = createArticleUseCase.execute(input);
        return ArticlePayload.of(output.article());
    }

    @Override
    public ArticlePayload update(final Long id, final UpdateArticlePayload payload) {
        final var input = payload.input(id);
        final var output = updateArticleUseCase.execute(input);
        return ArticlePayload.of(output.article());
    }

    @Override
    public void deleteById(final Long id) {
        articleService.deleteById(id);
    }

}
