package tech.devaneio.cs.entrypoint.web.payload.request;

import tech.devaneio.cs.core.usecase.CreateArticleUseCase;

public record CreateArticlePayload(String title, String description, String content) {

    public CreateArticleUseCase.Input input() {
        return CreateArticleUseCase.Input.builder()
            .title(title())
            .description(description())
            .content(content())
            .build();
    }

}
