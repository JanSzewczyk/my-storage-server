package jrs.mystorage.util.validator;

import jrs.mystorage.employee.repository.EmployeeRepository;
import jrs.mystorage.owner.repository.OwnerRepository;
import jrs.mystorage.util.validator.annotation.UniqueEmailValid;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailConstraintValidator implements ConstraintValidator<UniqueEmailValid, String> {

    private final OwnerRepository ownerRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return ownerRepository.findByEmail(email).isEmpty() && employeeRepository.findByEmail(email).isEmpty();
    }
}
