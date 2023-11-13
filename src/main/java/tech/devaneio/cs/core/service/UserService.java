package tech.devaneio.cs.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devaneio.cs.core.entity.User;
import tech.devaneio.cs.core.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(final User user) {
        return userRepository.save(user);
    }

}
