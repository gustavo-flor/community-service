package tech.devaneio.cs.entrypoint.web.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tech.devaneio.cs.core.entity.User;
import tech.devaneio.cs.core.service.TokenService;
import tech.devaneio.cs.core.service.UserService;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

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
            .filter(this::isBearerAuthentication)
            .map(this::extractToken)
            .map(tokenService::parseToken)
            .filter(this::hasExpiration)
            .filter(this::isExpired)
            .map(Claims::getSubject)
            .map(this::getUserByUsername)
            .map(this::generateAuthentication)
            .ifPresent(it -> SecurityContextHolder.getContext().setAuthentication(it));
        filterChain.doFilter(request, response);
    }

    private boolean isBearerAuthentication(final String authorization) {
        return authorization.startsWith(BEARER_AUTHENTICATION_PREFIX);
    }

    private String extractToken(final String authorization) {
        return authorization.substring(BEARER_AUTHENTICATION_PREFIX.length());
    }

    private boolean hasExpiration(final Claims claims) {
        return nonNull(claims.getExpiration());
    }

    private boolean isExpired(final Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    private UserDetails getUserByUsername(final String username) {
        return userDetailsService.loadUserByUsername(username);
    }

    private Authentication generateAuthentication(final UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
