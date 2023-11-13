package tech.devaneio.cs.entrypoint.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] UNAUTHENTICATED_ENDPOINTS = {
        "/webjars/**", "/error"
    };
    private static final String[] UNAUTHENTICATED_GET_ENDPOINTS = {
        "/", "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs.yaml", "/health/**"
    };
    private static final String[] UNAUTHENTICATED_POST_ENDPOINTS = {};

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(this::authorizeHttpRequests)
            .build();
    }

    private void authorizeHttpRequests(final AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry.requestMatchers(UNAUTHENTICATED_ENDPOINTS)
            .permitAll()
            .requestMatchers(POST, UNAUTHENTICATED_POST_ENDPOINTS)
            .permitAll()
            .requestMatchers(GET, UNAUTHENTICATED_GET_ENDPOINTS)
            .permitAll()
            .anyRequest()
            .authenticated();
    }

}
