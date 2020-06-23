package jrs.mystorage.util.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PasswordValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @InjectMocks
    private PasswordConstraintValidator passwordConstraintValidator;

    @Test
    void shouldReturnTrueIfPasswordIsValid() {
        assertTrue(passwordConstraintValidator.isValid("zaq1@WSXcde3", constraintValidatorContext));
    }

    @Test
    void shouldReturnFalseIfPasswordIsNotValid() {
        assertFalse(passwordConstraintValidator.isValid("zafsdfsdde3", constraintValidatorContext));
    }
}