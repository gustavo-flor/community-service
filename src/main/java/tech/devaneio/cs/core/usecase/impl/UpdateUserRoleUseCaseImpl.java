package tech.devaneio.cs.core.usecase.impl;

import lombok.RequiredArgsConstructor;
import tech.devaneio.cs.core.exception.UserNotFoundException;
import tech.devaneio.cs.core.mapping.UseCase;
import tech.devaneio.cs.core.service.UserService;
import tech.devaneio.cs.core.usecase.UpdateUserRoleUseCase;

@UseCase
@RequiredArgsConstructor
public class UpdateUserRoleUseCaseImpl implements UpdateUserRoleUseCase {

    private final UserService userService;

    @Override
    public Output execute(final Input input) {
        final var user = userService.findById(input.id())
            .orElseThrow(UserNotFoundException::new);
        user.setRole(input.role());
        userService.save(user);
        return new Output(user);
    }

}
