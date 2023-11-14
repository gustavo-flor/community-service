package tech.devaneio.cs.entrypoint.web.payload.request;

import tech.devaneio.cs.core.usecase.CreateUserUseCase;

public record CreateUserPayload(String fullName, String email, String password) {

    public CreateUserUseCase.Input input() {
        return CreateUserUseCase.Input.builder()
            .fullName(fullName())
            .email(email())
            .password(password())
            .build();
    }

}
