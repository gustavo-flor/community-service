package tech.devaneio.cs.core.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.devaneio.cs.core.entity.User;
import tech.devaneio.cs.core.mapping.UseCase;
import tech.devaneio.cs.core.service.UserService;
import tech.devaneio.cs.core.usecase.CreateUserUseCase;

@UseCase
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Output execute(final Input input) {
        final var user = createUser(input);
        return new Output(user);
    }

    private User createUser(final Input input) {
        final var user = input.user();
        final var encodedPassword = passwordEncoder.encode(input.password());
        user.setPassword(encodedPassword);
        return userService.save(user);
    }

}
