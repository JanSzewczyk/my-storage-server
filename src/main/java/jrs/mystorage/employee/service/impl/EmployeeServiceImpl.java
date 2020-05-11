package jrs.mystorage.employee.service.impl;

import jrs.mystorage.employee.dto.CEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import jrs.mystorage.employee.dto.UEmployeeDto;
import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.employee.repository.EmployeeRepository;
import jrs.mystorage.employee.service.EmployeeService;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.repository.OwnerRepository;
import jrs.mystorage.storage.model.Storage;
import jrs.mystorage.storage.repository.StorageRepository;
import jrs.mystorage.utils.exception.NotFoundException;
import jrs.mystorage.utils.mapper.EmployeeMapper;
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
            Storage storage = storageRepository.findByStorageIdAndOwnerEmail(newEmployee.getStorageId(), ownerEmail)
                    .orElseThrow(NotFoundException::new);

            employee.setStorage(storage);
        }

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

    //TODO Refactor code
    @Override
    public EmployeeDto updateEmployee(String ownerEmail, UUID employeeId, UEmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(NotFoundException::new);

        if (updatedEmployee.getPassword().equals("")) {
            updatedEmployee.setPassword(employee.getPassword());
        }
        System.out.println(updatedEmployee);
        if ((employee.getStorage() == null && updatedEmployee.getStorageId() != null) || (updatedEmployee.getStorageId() != null && employee.getStorage().getStorageId() != updatedEmployee.getStorageId())) {
            Storage storage = storageRepository.findByStorageIdAndOwnerEmail(updatedEmployee.getStorageId(), ownerEmail)
                    .orElseThrow(NotFoundException::new);
            employee.setStorage(storage);
        } else if (updatedEmployee.getStorageId() == null ) {
            employee.setStorage(null);
        }


        Employee employee1 = employeeMapper.updateEntity(updatedEmployee, employee);
        employeeRepository.save(employee1);
        return employeeMapper.toDto(employee);
    }
}
