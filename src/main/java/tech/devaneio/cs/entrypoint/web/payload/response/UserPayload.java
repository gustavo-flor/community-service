package tech.devaneio.cs.entrypoint.web.payload.response;

import lombok.Builder;
import tech.devaneio.cs.core.entity.Role;
import tech.devaneio.cs.core.entity.User;

import java.time.LocalDateTime;

@Builder
public record UserPayload(Long id,
                          String fullName,
                          String email,
                          Role role,
                          LocalDateTime createdAt,
                          LocalDateTime updatedAt) {

    public static UserPayload of(final User user) {
        return UserPayload.builder()
            .id(user.getId())
            .fullName(user.getFullName())
            .email(user.getEmail())
            .role(user.getRole())
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .build();
    }

}
