package tech.devaneio.cs.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.devaneio.cs.core.service.UserService;

import static java.text.MessageFormat.format;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(final UserService userService) {
        return username -> userService.findByEmail(username)
            .orElseThrow(() -> {
                final var message = format("User not found by e-mail equals to {0}", username);
                return new UsernameNotFoundException(message);
            });
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
        final PasswordEncoder passwordEncoder,
        final UserDetailsService userDetailsService
    ) {
        final var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

}
