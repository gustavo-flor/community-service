package tech.devaneio.cs.entrypoint.web.payload.request;

import tech.devaneio.cs.core.usecase.PublishArticleUseCase;

public record PublishArticlePayload(Long id) {

    public PublishArticleUseCase.Input input(final Long userId) {
        return new PublishArticleUseCase.Input(id(), userId);
    }

}
