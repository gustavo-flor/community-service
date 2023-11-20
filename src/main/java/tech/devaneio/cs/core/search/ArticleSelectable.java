package tech.devaneio.cs.core.search;

import tech.devaneio.cs.core.entity.ArticleField;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ArticleSelectable implements Selectable {

    private final Set<String> selections = new HashSet<>();

    public static ArticleSelectable allFields() {
        return new ArticleSelectable()
            .select(ArticleField.selectableFields());
    }

    public ArticleSelectable select(final Collection<ArticleField> fields) {
        fields.forEach(this::select);
        return this;
    }

    @Override
    public Set<String> selections() {
        return selections;
    }

    private void select(final ArticleField field) {
        if (field.isSelectable()) {
            selections.add(field.getPropertyName());
        }
    }

}
