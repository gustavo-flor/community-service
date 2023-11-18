package tech.devaneio.cs.entrypoint.web.payload.request;

import tech.devaneio.cs.core.usecase.UpdateUserUseCase;

public record UpdateUserPayload(String fullName) {

    public UpdateUserUseCase.Input input(final Long id) {
        return UpdateUserUseCase.Input.builder()
            .fullName(fullName())
            .id(id)
            .build();
    }

}
