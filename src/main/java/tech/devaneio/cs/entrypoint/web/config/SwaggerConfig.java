package tech.devaneio.cs.entrypoint.web.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_KEY = "Bearer Authentication";
    private static final String SECURITY_SCHEME = "bearer";
    private static final String BEARER_FORMAT = "JWT";

    @Bean
    public OpenAPI openAPI() {
        final var securityRequirement = new SecurityRequirement()
            .addList(SECURITY_KEY);
        final var securityScheme = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme(SECURITY_SCHEME)
            .bearerFormat(BEARER_FORMAT);
        final var components = new Components()
            .addSecuritySchemes(SECURITY_KEY, securityScheme);
        return new OpenAPI()
            .addSecurityItem(securityRequirement)
            .components(components);
    }

}
