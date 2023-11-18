package tech.devaneio.cs.entrypoint.web.payload.request;

import tech.devaneio.cs.core.entity.UserRole;
import tech.devaneio.cs.core.usecase.UpdateUserRoleUseCase;

public record UpdateUserRolePayload(UserRole value) {

    public UpdateUserRoleUseCase.Input input(final Long id) {
        return UpdateUserRoleUseCase.Input.builder()
            .id(id)
            .role(value())
            .build();
    }

}
