package jrs.mystorage.util.database;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isBlank;

public class SpecificationBuildHelper {

    public static <T> Specification<T> containsTextInAttributes(String searchString, List<String> attributes) {
        if (isBlank(searchString)) {
            return null;
        }

        String finalText = searchString.contains("%") ? searchString : "%" + searchString + "%";

        return (root, query, builder) -> builder.or(root.getModel().getDeclaredSingularAttributes()
                .stream()
                .filter(a -> attributes.contains(a.getName()))
                .map(a -> builder.like(builder.lower(root.get(a.getName()).as(String.class)), finalText.toLowerCase()))
                .toArray(Predicate[]::new)
        );
    }

    public static <T> Specification<T> fieldEquals(String fieldName, Object value) {
        return (root, query, cb) -> cb.equal(root.get(fieldName), value);
    }
}
