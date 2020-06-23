package jrs.mystorage.util.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class EmailValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @InjectMocks
    private EmailConstraintValidator emailConstraintValidator;

    @Test
    void shouldReturnTrueIfEmailIsValid() {
        assertTrue(emailConstraintValidator.isValid("xyz.zzx@gmail.com", constraintValidatorContext));
    }

    @Test
    void shouldReturnFalseIfEmailIsNotValid() {
        assertFalse(emailConstraintValidator.isValid("asda.safsf.com", constraintValidatorContext));
    }
}