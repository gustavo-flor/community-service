package tech.devaneio.cs.entrypoint.web.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import tech.devaneio.cs.core.exception.UserNotFoundException;
import tech.devaneio.cs.core.service.UserService;
import tech.devaneio.cs.core.usecase.CreateUserUseCase;
import tech.devaneio.cs.core.usecase.UpdateUserRoleUseCase;
import tech.devaneio.cs.core.usecase.UpdateUserUseCase;
import tech.devaneio.cs.entrypoint.web.controller.UserController;
import tech.devaneio.cs.entrypoint.web.exception.NotFoundException;
import tech.devaneio.cs.entrypoint.web.payload.request.CreateUserPayload;
import tech.devaneio.cs.entrypoint.web.payload.request.UpdateUserPayload;
import tech.devaneio.cs.entrypoint.web.payload.request.UpdateUserRolePayload;
import tech.devaneio.cs.entrypoint.web.payload.response.UserPayload;
import tech.devaneio.cs.entrypoint.web.query.PageableQuery;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final UpdateUserRoleUseCase updateUserRoleUseCase;

    @Override
    public Page<UserPayload> findAll(final PageableQuery query) {
        return userService.findAll(query.pageRequest()).map(UserPayload::of);
    }

    @Override
    public UserPayload create(final CreateUserPayload payload) {
        final var input = payload.input();
        final var output = createUserUseCase.execute(input);
        return UserPayload.of(output.user());
    }

    @Override
    public UserPayload update(final Long id, final UpdateUserPayload payload) {
        try {
            final var input = payload.input(id);
            final var output = updateUserUseCase.execute(input);
            return UserPayload.of(output.user());
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    public UserPayload updateRole(final Long id, final UpdateUserRolePayload payload) {
        try {
            final var input = payload.input(id);
            final var output = updateUserRoleUseCase.execute(input);
            return UserPayload.of(output.user());
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

}
