package tech.devaneio.cs.core.usecase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;
import tech.devaneio.cs.core.entity.Role;
import tech.devaneio.cs.core.entity.User;
import tech.devaneio.cs.core.exception.UserNotFoundException;

@Validated
public interface UpdateUserRoleUseCase {

    Output execute(@Valid @NotNull Input input) throws UserNotFoundException;

    @Builder
    record Input(@NotNull @Positive Long id, @NotNull Role role) {
    }

    record Output(User user) {
    }

}
