package jrs.mystorage.utils.validator.annotation;

import jrs.mystorage.utils.validator.UniqueEmailConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = UniqueEmailConstraintValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface UniqueEmailValid {

    String message() default "User with this email already exists.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
