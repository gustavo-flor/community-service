package tech.devaneio.cs.core.usecase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;
import tech.devaneio.cs.core.entity.User;

import static tech.devaneio.cs.core.entity.Role.VISITOR;

@Validated
public interface CreateUserUseCase {

    Output execute(@Valid @NotNull Input input);

    @Builder
    record Input(@NotBlank String fullName,
                 @Email String email,
                 @NotBlank String password) {

        public User user() {
            return User.builder()
                .fullName(fullName())
                .email(email())
                .role(VISITOR)
                .build();
        }

    }

    record Output(User user) {
    }

}
