package tech.devaneio.cs.entrypoint.web.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tech.devaneio.cs.core.context.AuthContext;
import tech.devaneio.cs.core.usecase.UpdateUserUseCase;
import tech.devaneio.cs.entrypoint.web.controller.ProfileController;
import tech.devaneio.cs.entrypoint.web.payload.request.UpdateUserPayload;
import tech.devaneio.cs.entrypoint.web.payload.response.UserPayload;

@RestController
@RequiredArgsConstructor
public class ProfileControllerImpl implements ProfileController {

    private final UpdateUserUseCase updateUserUseCase;

    @Override
    public UserPayload update(final UpdateUserPayload payload) {
        final var currentUser = AuthContext.getCurrentUser().orElseThrow();
        final var input = payload.input(currentUser.getId());
        final var output = updateUserUseCase.execute(input);
        return UserPayload.of(output.user());
    }

}
