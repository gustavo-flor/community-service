package tech.devaneio.cs.entrypoint.web.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.devaneio.cs.core.search.ArticleSearchable;

import static tech.devaneio.cs.core.entity.ArticleField.USER_ID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSearchQuery extends PageableQuery {

    private Long userId;

    public ArticleSearchable searchable() {
        final var ignoreIfNull = true;
        return new ArticleSearchable()
            .add(USER_ID, userId, ignoreIfNull);
    }

}
