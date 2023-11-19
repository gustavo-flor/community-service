package tech.devaneio.cs.core.search;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public interface Searchable<T> {

    Map<String, Object> filters();

    default Specification<T> specification() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final var predicates = filters()
                .entrySet()
                .stream()
                .map(it -> criteriaBuilder.equal(root.get(it.getKey()), it.getValue()))
                .toList()
                .toArray(Predicate[]::new);
            return criteriaBuilder.and(predicates);
        };
    }

}
