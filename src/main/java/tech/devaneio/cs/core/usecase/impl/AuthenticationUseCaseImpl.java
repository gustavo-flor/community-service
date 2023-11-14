package tech.devaneio.cs.core.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import tech.devaneio.cs.core.entity.User;
import tech.devaneio.cs.core.mapping.UseCase;
import tech.devaneio.cs.core.service.TokenService;
import tech.devaneio.cs.core.usecase.AuthenticationUseCase;

import static java.text.MessageFormat.format;

@UseCase
@RequiredArgsConstructor
public class AuthenticationUseCaseImpl implements AuthenticationUseCase {

    private static final String AUTHORIZATION_TEMPLATE = "Bearer {0}";

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public Output execute(final Input input) {
        final var authentication = authenticationManager.authenticate(input.authentication());
        final var user = (User) authentication.getPrincipal();
        final var token = tokenService.generateToken(user);
        final var authorization = format(AUTHORIZATION_TEMPLATE, token);
        return new Output(authorization, user);
    }
}
