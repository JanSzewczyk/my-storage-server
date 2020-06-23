package jrs.mystorage.util.database;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationBuildHelper {

    public static <T> Specification<T> fieldEquals(String fieldName, Object value) {
        return (root, query, cb) -> cb.equal(root.get(fieldName), value);
    }
}
