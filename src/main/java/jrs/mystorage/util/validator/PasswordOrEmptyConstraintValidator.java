package jrs.mystorage.util.validator;

import jrs.mystorage.util.validator.annotation.PasswordOrEmptyValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordOrEmptyConstraintValidator implements ConstraintValidator<PasswordOrEmptyValid, String> {

    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password != null && (password.matches(PASSWORD_PATTERN) || password.equals(""));
    }
}
