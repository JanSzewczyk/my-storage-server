package jrs.mystorage.employee.service.impl;

import jrs.mystorage.employee.dto.CEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import jrs.mystorage.employee.dto.EmployeeViewDto;
import jrs.mystorage.employee.dto.UEmployeeDto;
import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.employee.repository.EmployeeRepository;
import jrs.mystorage.employee.service.EmployeeService;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.repository.OwnerRepository;
import jrs.mystorage.storage.model.Storage;
import jrs.mystorage.storage.repository.StorageRepository;
import jrs.mystorage.util.exception.NotFoundException;
import jrs.mystorage.util.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final PagedResourcesAssembler<Employee> employeePagedResourcesAssembler;
    private final EmployeeRepository employeeRepository;
    private final OwnerRepository ownerRepository;
    private final EmployeeMapper employeeMapper;
    private final StorageRepository storageRepository;

    @Override
    public EmployeeDto createEmployee(String ownerEmail, CEmployeeDto newEmployee) {
        Owner owner = ownerRepository.findByEmail(ownerEmail)
                .orElseThrow(NotFoundException::new);

        Employee employee = employeeMapper.toEntity(newEmployee);
        employee.setOwner(owner);

        if (newEmployee.getStorageId() != null) {
            Storage storage = storageRepository.findByIdAndOwnerEmail(newEmployee.getStorageId(), ownerEmail)
                    .orElseThrow(NotFoundException::new);

            employee.setStorage(storage);
        }

        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public PagedModel<EmployeeViewDto> getEmployeesByOwnerEmail(String ownerEmail, Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAllByOwnerEmail(ownerEmail, pageable);
        return employeePagedResourcesAssembler.toModel(employees, employeeMapper::toViewDto);
    }

    @Override
    public PagedModel<EmployeeViewDto> getEmployeesByStorage(String ownerEmail, UUID storageId, Pageable pageable) {
        Page<Employee> employees = employeeRepository
                .findAllByOwnerEmailAndStorageId(ownerEmail, storageId, pageable);
        return employeePagedResourcesAssembler.toModel(employees, employeeMapper::toViewDto);
    }

    @Override
    public EmployeeDto getEmployees(String ownerEmail, UUID employeeId) {
        Employee employee = employeeRepository.findByIdAndOwnerEmail(employeeId, ownerEmail)
                .orElseThrow(NotFoundException::new);
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto removeEmployee(String ownerEmail, UUID employeeId) {
        Employee employee = employeeRepository.findByIdAndOwnerEmail(employeeId, ownerEmail)
                .orElseThrow(NotFoundException::new);
        employeeRepository.delete(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(String ownerEmail, UUID employeeId, UEmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(NotFoundException::new);

        if (updatedEmployee.getPassword().equals("")) {
            updatedEmployee.setPassword(employee.getPassword());
        }

        if ((employee.getStorage() == null || employee.getStorage().getId() != updatedEmployee.getStorageId()) && updatedEmployee.getStorageId() != null) {
            Storage storage = storageRepository.findByIdAndOwnerEmail(updatedEmployee.getStorageId(), ownerEmail)
                    .orElseThrow(NotFoundException::new);
            employee.setStorage(storage);
        } else if (updatedEmployee.getStorageId() == null ) {
            employee.setStorage(null);
        }

        employeeMapper.updateEntity(updatedEmployee, employee);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }
}
