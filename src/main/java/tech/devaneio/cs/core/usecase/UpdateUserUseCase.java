package tech.devaneio.cs.core.usecase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;
import tech.devaneio.cs.core.validator.mapping.FullName;
import tech.devaneio.cs.core.entity.User;
import tech.devaneio.cs.core.exception.UserNotFoundException;

@Validated
public interface UpdateUserUseCase {

    Output execute(@Valid @NotNull Input input) throws UserNotFoundException;

    @Builder
    record Input(@NotNull Long id,
                 @NotBlank @FullName String fullName) {

        public User user() {
            return User.builder()
                .fullName(fullName())
                .build();
        }

    }

    record Output(User user) {
    }

}
