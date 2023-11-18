package tech.devaneio.cs.entrypoint.web.payload.request;

import tech.devaneio.cs.core.usecase.UpdateArticleUseCase;

public record UpdateArticlePayload(String title, String description, String content) {

    public UpdateArticleUseCase.Input input(final Long id) {
        return UpdateArticleUseCase.Input.builder()
            .id(id)
            .title(title())
            .description(description())
            .content(content())
            .build();
    }

}
