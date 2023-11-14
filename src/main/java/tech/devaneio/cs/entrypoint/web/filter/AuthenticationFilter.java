package tech.devaneio.cs.entrypoint.web.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tech.devaneio.cs.core.service.TokenService;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static java.text.MessageFormat.format;
import static java.util.Objects.nonNull;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER_AUTHENTICATION_PREFIX = "Bearer ";

    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
        @NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response,
        final FilterChain filterChain
    ) throws ServletException, IOException {
        Optional.ofNullable(request.getHeader(AUTHORIZATION))
            .filter(it -> it.startsWith(BEARER_AUTHENTICATION_PREFIX))
            .map(it -> it.substring(BEARER_AUTHENTICATION_PREFIX.length()))
            .map(this::parseToken)
            .filter(it -> nonNull(it.getExpiration()) && it.getExpiration().after(new Date()))
            .map(Claims::getSubject)
            .map(this::getUserByUsername)
            .map(it -> new UsernamePasswordAuthenticationToken(it, null, it.getAuthorities()))
            .ifPresent(it -> {
                it.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(it);
            });
        filterChain.doFilter(request, response);
    }

    private Claims parseToken(final String token) {
        try {
            return tokenService.parseToken(token);
        } catch (RuntimeException e) {
            final var message = format("Invalid/expired token with value equals to [{0}]", token);
            log.debug(message, e);
            return new DefaultClaims();
        }
    }

    private UserDetails getUserByUsername(final String username) {
        try {
            return userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            log.warn("User not found on authentication filter using username received on authorization token", e);
            throw e;
        }
    }

}
