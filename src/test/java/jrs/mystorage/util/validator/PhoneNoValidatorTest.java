package jrs.mystorage.util.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PhoneNoValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @InjectMocks
    private PhoneNoConstraintValidator phoneNoConstraintValidator;

    @Test
    void shouldReturnTrueIfPhoneNoIsValid() {
        assertTrue(phoneNoConstraintValidator.isValid("+48432342234", constraintValidatorContext));
    }

    @Test
    void shouldReturnFalseIfPhoneNoIsNotValid() {
        assertFalse(phoneNoConstraintValidator.isValid("323422434", constraintValidatorContext));
    }
}