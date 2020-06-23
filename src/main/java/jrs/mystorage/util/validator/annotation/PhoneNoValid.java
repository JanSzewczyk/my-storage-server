package jrs.mystorage.util.validator.annotation;

import jrs.mystorage.util.validator.PhoneNoConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PhoneNoConstraintValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface PhoneNoValid {

    String message() default "Invalid phone number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
