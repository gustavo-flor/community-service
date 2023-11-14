package tech.devaneio.cs.entrypoint.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tech.devaneio.cs.entrypoint.web.filter.AuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private static final String[] UNAUTHENTICATED_ENDPOINTS = {
        "/webjars/**", "/error"
    };
    private static final String[] UNAUTHENTICATED_GET_ENDPOINTS = {
        "/", "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs.yaml", "/health"
    };
    private static final String[] UNAUTHENTICATED_POST_ENDPOINTS = {};

    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(this::authorizeHttpRequests)
            .sessionManagement(it -> it.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
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
