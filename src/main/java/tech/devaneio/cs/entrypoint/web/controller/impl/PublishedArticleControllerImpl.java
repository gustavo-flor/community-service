package tech.devaneio.cs.entrypoint.web.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import tech.devaneio.cs.core.exception.ArticleNotFoundException;
import tech.devaneio.cs.core.service.ArticleService;
import tech.devaneio.cs.core.usecase.PublishArticleUseCase;
import tech.devaneio.cs.entrypoint.web.controller.PublishedArticleController;
import tech.devaneio.cs.entrypoint.web.payload.request.PublishArticlePayload;
import tech.devaneio.cs.entrypoint.web.payload.response.ArticlePayload;
import tech.devaneio.cs.entrypoint.web.query.PageableQuery;

@RestController
@RequiredArgsConstructor
public class PublishedArticleControllerImpl implements PublishedArticleController {

    private final ArticleService articleService;
    private final PublishArticleUseCase publishArticleUseCase;

    @Override
    public Page<ArticlePayload> findAll(final PageableQuery query) {
        return articleService.findAllPublished(query.pageRequest()).map(ArticlePayload::of);
    }

    @Override
    public ArticlePayload findById(final Long id) {
        return articleService.findPublishedById(id)
            .map(ArticlePayload::of)
            .orElseThrow(ArticleNotFoundException::new);
    }

    @Override
    public ArticlePayload publish(final PublishArticlePayload payload) {
        final var input = payload.input();
        final var output = publishArticleUseCase.execute(input);
        return ArticlePayload.of(output.article());
    }

}
