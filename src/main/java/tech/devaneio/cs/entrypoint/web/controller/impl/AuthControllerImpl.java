package tech.devaneio.cs.entrypoint.web.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tech.devaneio.cs.core.usecase.AuthenticationUseCase;
import tech.devaneio.cs.entrypoint.web.controller.AuthController;
import tech.devaneio.cs.entrypoint.web.payload.request.LoginPayload;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthenticationUseCase authenticationUseCase;

    @Override
    public ResponseEntity<Void> login(final LoginPayload payload) {
        final var input = payload.input();
        final var output = authenticationUseCase.execute(input);
        return ResponseEntity.noContent()
            .header(AUTHORIZATION, output.authorization())
            .build();
    }

}
