package tech.devaneio.cs.core.config.properties;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class TokenConfigProperties {

    private String signingKey;
    private SignatureAlgorithm signatureAlgorithm;
    private Long durationInMillis;

}
