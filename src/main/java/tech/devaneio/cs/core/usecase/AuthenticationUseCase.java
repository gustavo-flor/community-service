package tech.devaneio.cs.core.usecase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import tech.devaneio.cs.core.entity.User;

@Validated
public interface AuthenticationUseCase {

    Output execute(@Valid @NotNull Input input);

    record Input(@Email String email, @NotBlank String password) {

        public UsernamePasswordAuthenticationToken authentication() {
            return new UsernamePasswordAuthenticationToken(email(), password());
        }

    }

    record Output(String authorization, User user) {
    }

}
