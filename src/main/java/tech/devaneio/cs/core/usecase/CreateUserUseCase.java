package tech.devaneio.cs.core.usecase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;
import tech.devaneio.cs.core.exception.ConflictException;
import tech.devaneio.cs.core.validator.mapping.FullName;
import tech.devaneio.cs.core.entity.User;
import tech.devaneio.cs.core.validator.mapping.Password;

import static tech.devaneio.cs.core.entity.UserRole.VISITOR;

@Validated
public interface CreateUserUseCase {

    Output execute(@Valid @NotNull Input input) throws ConflictException;

    @Builder
    record Input(@NotBlank @FullName String fullName,
                 @NotBlank @Email String email,
                 @NotBlank @Password String password) {

        public User user() {
            return User.builder()
                .fullName(fullName())
                .email(email().toLowerCase())
                .role(VISITOR)
                .build();
        }

    }

    record Output(User user) {
    }

}
