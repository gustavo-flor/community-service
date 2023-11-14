package tech.devaneio.cs.entrypoint.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devaneio.cs.core.usecase.AuthenticationUseCase;
import tech.devaneio.cs.entrypoint.web.payload.request.LoginPayload;
import tech.devaneio.cs.entrypoint.web.payload.response.UserPayload;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody final LoginPayload payload) {
        final var input = payload.input();
        final var output = authenticationUseCase.execute(input);
        return ResponseEntity.noContent()
            .header(AUTHORIZATION, output.authorization())
            .build();
    }

}
