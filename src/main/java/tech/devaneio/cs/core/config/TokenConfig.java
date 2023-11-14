package tech.devaneio.cs.core.config;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.devaneio.cs.core.config.properties.TokenConfigProperties;

@Configuration
@RequiredArgsConstructor
public class TokenConfig {

    private final TokenConfigProperties properties;

    @Bean
    public JwtParser jwtParser() {
        return Jwts.parser()
            .setSigningKey(properties.getSigningKey());
    }

}
