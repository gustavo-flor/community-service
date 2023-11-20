package tech.devaneio.cs.core.search;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import tech.devaneio.cs.core.entity.Article;

public record ArticleSearch(ArticleSelectable selectable,
                            ArticleFilterable filterable,
                            Pageable pageable) {

    public Specification<Article> specification() {
        return filterable().specification();
    }

}
