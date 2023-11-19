package tech.devaneio.cs.core.entity;

import com.fasterxml.jackson.databind.EnumNamingStrategies;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public enum ArticleField {

    ID(false, Long.class),
    TITLE( false, String.class),
    DESCRIPTION(false, String.class),
    CONTENT(false, String.class),
    STATUS(false, ArticleStatus.class),
    USER_ID(true, Long.class),
    USER(false, User.class),
    PUBLISHED_AT(false, LocalDateTime.class),
    CREATED_AT(false, LocalDateTime.class),
    UPDATED_AT(false, LocalDateTime.class);

    private final boolean filterable;
    private final Class<?> type;

    public String getPropertyName() {
        return EnumNamingStrategies.CamelCaseStrategy.INSTANCE.convertEnumToExternalName(name());
    }

    public boolean isFilterable(final Object value) {
        return isFilterable() && (value == null || getType().isInstance(value));
    }

}
