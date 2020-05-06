package jrs.mystorage.user.service.impl;

import jrs.mystorage.auth.model.Role;
import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.employee.repository.EmployeeRepository;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.repository.OwnerRepository;
import jrs.mystorage.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final OwnerRepository ownerRepository;
    private final EmployeeRepository employeeRepository;

    public Optional<User> findAuthUserByEmail(String email) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        Optional<Owner> ofOwner = ownerRepository.findByEmail(email);
        if (ofOwner.isPresent()) {
            Owner owner = ofOwner.get();
            authorities.add(new SimpleGrantedAuthority(Role.OWNER.toString()));
            return Optional.of(new User(owner.getEmail(), owner.getPassword(), authorities));
        }

        Optional<Employee> ofEmployee = employeeRepository.findByEmail(email);
        if (ofEmployee.isPresent()) {
            Employee employee = ofEmployee.get();
            authorities.add(new SimpleGrantedAuthority(Role.EMPLOYEE.toString()));
            return Optional.of(new User(employee.getEmail(), employee.getPassword(), authorities));
        }
        // TODO create Custom AuthUser
        return Optional.empty();
    }
}
