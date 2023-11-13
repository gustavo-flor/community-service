package tech.devaneio.cs.core.usecase;

import tech.devaneio.cs.core.entity.User;

public interface CreateUserUseCase {

    Output execute(Input input);

    record Input(String email, String password) {

        public User user() {
            return User.builder()
                .email(email())
                .build();
        }

    }

    record Output(User user) {
    }

}
