package tech.devaneio.cs.core.search;

import lombok.RequiredArgsConstructor;
import tech.devaneio.cs.core.entity.Article;
import tech.devaneio.cs.core.entity.ArticleField;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ArticleFilterable implements Filterable<Article> {

    private final Map<ArticleField, Object> filters = new EnumMap<>(ArticleField.class);

    public ArticleFilterable filter(final ArticleField field, final Object value, final boolean ignoreIfNull) {
        final var shouldBeIgnored = ignoreIfNull && value == null;
        return shouldBeIgnored ? this : filter(field, value);
    }

    public ArticleFilterable filter(final ArticleField field, final Object value) {
        if (field.isFilterable(value)) {
            filters.put(field, value);
            return this;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Map<String, Object> filters() {
        return filters.entrySet()
            .stream()
            .map(it -> Map.entry(it.getKey().getPropertyName(), it.getValue()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
