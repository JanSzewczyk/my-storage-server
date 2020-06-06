package jrs.mystorage.utils.validator;

import jrs.mystorage.utils.validator.annotation.PhoneNoValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNoConstraintValidator implements ConstraintValidator<PhoneNoValid, String> {

    public static final String PHONE_NUMBER_PATTERN = "^\\+([0-9]|-){5,}";

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext constraintValidatorContext) {
        return phoneNo != null && phoneNo.matches(PHONE_NUMBER_PATTERN);
    }
}
