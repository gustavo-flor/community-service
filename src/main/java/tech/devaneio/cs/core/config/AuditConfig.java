package tech.devaneio.cs.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import tech.devaneio.cs.core.context.AuthContext;
import tech.devaneio.cs.core.entity.User;

@Configuration
@EnableJpaAuditing
public class AuditConfig {

    @Bean
    public AuditorAware<User> auditorAware() {
        return AuthContext::getCurrentUser;
    }

}
