package tech.devaneio.cs.core.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {

    VISITOR,
    SUBSCRIBER,
    MODERATOR,
    ADMINISTRATOR;

    public GrantedAuthority getAuthority() {
        return new SimpleGrantedAuthority("ROLE_" + name());
    }

}
