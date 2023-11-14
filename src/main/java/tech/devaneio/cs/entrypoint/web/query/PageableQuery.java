package tech.devaneio.cs.entrypoint.web.query;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageableQuery {

    private static final Integer DEFAULT_SIZE = 24;

    @NotNull
    @PositiveOrZero
    private Integer page = 0;

    public PageRequest pageRequest() {
        return PageRequest.of(page, DEFAULT_SIZE);
    }

}
