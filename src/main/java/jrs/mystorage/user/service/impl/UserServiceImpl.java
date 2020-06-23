package jrs.mystorage.user.service.impl;

import jrs.mystorage.auth.model.AuthGrantedAuthority;
import jrs.mystorage.auth.model.Role;
import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.employee.repository.EmployeeRepository;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.repository.OwnerRepository;
import jrs.mystorage.user.dto.UserDetailsDto;
import jrs.mystorage.user.service.UserService;
import jrs.mystorage.util.exception.NotFoundException;
import jrs.mystorage.util.mapper.EmployeeMapper;
import jrs.mystorage.util.mapper.OwnerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
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

    private final EmployeeMapper employeeMapper;
    private final OwnerMapper ownerMapper;

    public Optional<User> findAuthUserByEmail(String email) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        Optional<Owner> ofOwner = ownerRepository.findByEmail(email);
        if (ofOwner.isPresent()) {
            Owner owner = ofOwner.get();
            authorities.add(new AuthGrantedAuthority(Role.OWNER));
            return Optional.of(new User(owner.getEmail(), owner.getPassword(), authorities));
        }

        Optional<Employee> ofEmployee = employeeRepository.findByEmail(email);
        if (ofEmployee.isPresent()) {
            Employee employee = ofEmployee.get();
            authorities.add(new AuthGrantedAuthority(Role.EMPLOYEE));
            return Optional.of(new User(employee.getEmail(), employee.getPassword(), authorities));
        }

        return Optional.empty();
    }

    @Override
    public UserDetailsDto getUserDetails(String userEmail) {
        Optional<Owner> owner = ownerRepository.findByEmail(userEmail);
        if (owner.isPresent()) {
            return new UserDetailsDto(ownerMapper.toDto(owner.get()), Role.OWNER);
        }

        Optional<Employee> ofEmployee = employeeRepository.findByEmail(userEmail);
        if (ofEmployee.isPresent()) {
            return new UserDetailsDto(employeeMapper.toDto(ofEmployee.get()), Role.EMPLOYEE);
        }

        throw new NotFoundException();
    }

    @Override
    public Role getUserTypeByEmail(String userEmail) {
        if (ownerRepository.findByEmail(userEmail).isPresent())
            return Role.OWNER;

        if (employeeRepository.findByEmail(userEmail).isPresent())
            return Role.EMPLOYEE;

        throw new NotFoundException();
    }
}
