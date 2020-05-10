package jrs.mystorage.employee.service.impl;

import jrs.mystorage.employee.dto.CUEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.employee.repository.EmployeeRepository;
import jrs.mystorage.employee.service.EmployeeService;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.repository.OwnerRepository;
import jrs.mystorage.utils.exception.NotFoundException;
import jrs.mystorage.utils.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final PagedResourcesAssembler<Employee> employeePagedResourcesAssembler;
    private final EmployeeRepository employeeRepository;
    private final OwnerRepository ownerRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto createEmployee(String ownerEmail, CUEmployeeDto newEmployee) {
        Owner owner = ownerRepository.findByEmail(ownerEmail)
                .orElseThrow(NotFoundException::new);

        System.out.println(newEmployee);

        Employee employee = employeeMapper.toEntity(newEmployee);
        employee.setOwner(owner);

        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public PagedModel<EmployeeDto> getEmployeesByOwnerEmail(String ownerEmail, Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAllByOwnerEmail(ownerEmail, pageable);
        return employeePagedResourcesAssembler.toModel(employees, employeeMapper::toDto);
    }

    @Override
    public EmployeeDto getEmployees(String ownerEmail, UUID employeeId) {
        Employee employee = employeeRepository.findByEmployeeIdAndOwnerEmail(employeeId, ownerEmail)
                .orElseThrow(NotFoundException::new);

        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto removeEmployee(String ownerEmail, UUID employeeId) {
        Employee employee = employeeRepository.findByEmployeeIdAndOwnerEmail(employeeId, ownerEmail)
                .orElseThrow(NotFoundException::new);

        employeeRepository.delete(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(UUID employeeId, CUEmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(NotFoundException::new);
        employeeRepository.save(employeeMapper.updateEntity(updatedEmployee, employee));
        return employeeMapper.toDto(employee);
    }
}
