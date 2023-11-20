package tech.devaneio.cs.entrypoint.web.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.devaneio.cs.core.entity.ArticleStatus;
import tech.devaneio.cs.core.search.ArticleFilterable;
import tech.devaneio.cs.core.search.ArticleSearch;

import static tech.devaneio.cs.core.entity.ArticleField.STATUS;
import static tech.devaneio.cs.core.entity.ArticleField.USER_ID;
import static tech.devaneio.cs.core.search.ArticleSelectable.allFields;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSearchQuery extends PageableQuery {

    private Long userId;
    private ArticleStatus status;

    private ArticleFilterable filterable() {
        final var ignoreIfNull = true;
        return new ArticleFilterable()
            .filter(USER_ID, userId, ignoreIfNull)
            .filter(STATUS, status, ignoreIfNull);
    }

    public ArticleSearch search() {
        return new ArticleSearch(allFields(), filterable(), pageRequest());
    }

}
