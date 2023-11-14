package tech.devaneio.cs.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devaneio.cs.core.entity.User;
import tech.devaneio.cs.core.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(final User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

}
