package tech.devaneio.cs.core.entity;

import com.fasterxml.jackson.databind.EnumNamingStrategies;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum ArticleField {

    ID(true, false, Long.class),
    TITLE(true, false, String.class),
    DESCRIPTION(true,false, String.class),
    CONTENT(true,false, String.class),
    STATUS(true,true, ArticleStatus.class),
    USER_ID(true,true, Long.class),
    USER(false,false, User.class),
    PUBLISHED_AT(true,false, LocalDateTime.class),
    CREATED_AT(true,false, LocalDateTime.class),
    UPDATED_AT(true,false, LocalDateTime.class);

    private final boolean selectable;
    private final boolean filterable;
    private final Class<?> type;

    public static Set<ArticleField> selectableFields() {
        return Arrays.stream(values())
            .filter(ArticleField::isSelectable)
            .collect(Collectors.toSet());
    }

    public String getPropertyName() {
        return EnumNamingStrategies.CamelCaseStrategy.INSTANCE.convertEnumToExternalName(name());
    }

    public boolean isFilterable(final Object value) {
        return isFilterable() && (value == null || getType().isInstance(value));
    }

    public boolean isNotSelectable() {
        return !selectable;
    }

}
