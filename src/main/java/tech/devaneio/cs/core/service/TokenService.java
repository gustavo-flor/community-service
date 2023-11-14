package tech.devaneio.cs.core.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tech.devaneio.cs.core.config.properties.TokenConfigProperties;

import java.util.Date;

import static java.lang.System.currentTimeMillis;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenConfigProperties properties;
    private final JwtParser jwtParser;

    public Claims parseToken(final String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public String generateToken(final UserDetails userDetails) {
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(currentTimeMillis() + properties.getDurationInMillis()))
            .signWith(properties.getSignatureAlgorithm(), properties.getSigningKey())
            .compact();
    }

}
