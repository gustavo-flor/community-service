package tech.devaneio.cs.core.usecase;

import tech.devaneio.cs.core.entity.User;

import static tech.devaneio.cs.core.entity.Role.VISITOR;

public interface CreateUserUseCase {

    Output execute(Input input);

    record Input(String fullName, String email, String password) {

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
