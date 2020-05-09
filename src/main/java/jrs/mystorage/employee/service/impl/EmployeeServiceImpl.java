package jrs.mystorage.employee.service.impl;

import jrs.mystorage.employee.dto.CreateEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.employee.repository.EmployeeRepository;
import jrs.mystorage.employee.service.EmployeeService;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.repository.OwnerRepository;
import jrs.mystorage.utils.exception.NotFoundException;
import jrs.mystorage.utils.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OwnerRepository ownerRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeDto createEmployee(String ownerEmail, CreateEmployeeDto newEmployee) {
        Owner owner = ownerRepository.findByEmail(ownerEmail).orElseThrow(NotFoundException::new);

        Employee employee = employeeMapper.toEntity(newEmployee);
        employee.setOwner(owner);

        employeeRepository.save(employee);

        return employeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeDto> getEmployeeByOwnerEmail(String ownerEmail) {
        return employeeRepository
                .findAllByOwnerEmail(ownerEmail)
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }
}
