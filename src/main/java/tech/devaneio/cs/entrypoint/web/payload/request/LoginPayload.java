package tech.devaneio.cs.entrypoint.web.payload.request;

import tech.devaneio.cs.core.usecase.AuthenticationUseCase;

public record LoginPayload(String email, String password) {

    public AuthenticationUseCase.Input input() {
        return new AuthenticationUseCase.Input(email(), password());
    }

}
