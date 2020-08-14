package jrs.mystorage.employee.service.impl;

import jrs.mystorage.employee.dto.*;
import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.employee.repository.EmployeeRepository;
import jrs.mystorage.employee.service.EmployeeService;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.repository.OwnerRepository;
import jrs.mystorage.storage.model.Storage;
import jrs.mystorage.storage.repository.StorageRepository;
import jrs.mystorage.util.ShortID;
import jrs.mystorage.util.exception.ConflictException;
import jrs.mystorage.util.exception.NotFoundException;
import jrs.mystorage.util.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static jrs.mystorage.util.MessageTemplates.*;

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
        employee.setShortId(this.generateShortId());

        if (newEmployee.getStorageId() != null) {
            UUID storageId = newEmployee.getStorageId();
            Storage storage = storageRepository.findByIdAndOwnerEmail(storageId, ownerEmail)
                    .orElseThrow(() -> new NotFoundException(String.format(STORAGE_NOT_FOUND_MESSAGE_TEMPLATE, storageId)));

            employee.setStorage(storage);
        }

        return employeeMapper.toDto(employeeRepository.save(employee));
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
                .orElseThrow(() -> new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_MESSAGE_TEMPLATE, employeeId)));
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto removeEmployee(String ownerEmail, UUID employeeId) {
        Employee employee = employeeRepository.findByIdAndOwnerEmail(employeeId, ownerEmail)
                .orElseThrow(() -> new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_MESSAGE_TEMPLATE, employeeId)));
        employeeRepository.delete(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto changeEmployeeStorage(String ownerEmail, UUID employeeId, AssignStorageDto assignStorage) {
        Employee employee = employeeRepository.findByIdAndOwnerEmail(employeeId, ownerEmail)
                .orElseThrow(() -> new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_MESSAGE_TEMPLATE, employeeId)));

        if ((employee.getStorage() != null && !employee.getStorage().getId().equals(assignStorage.getStorageId())) ||
                (employee.getStorage() == null && assignStorage.getStorageId() != null)) {

            if (assignStorage.getStorageId() != null) {
                UUID storageId = assignStorage.getStorageId();
                Storage newStorage = storageRepository.findByIdAndOwnerEmail(storageId, ownerEmail)
                        .orElseThrow(() -> new NotFoundException(String.format(STORAGE_NOT_FOUND_MESSAGE_TEMPLATE, storageId)));
                employee.setStorage(newStorage);
            } else {
                employee.setStorage(null);
            }

        } else throw new ConflictException(String.format(EMPLOYEE_CHANGE_STORAGE_CONFLICT_MESSAGE_TEMPLATE, employeeId));

      return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto updateEmployee(String ownerEmail, UUID employeeId, UEmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_MESSAGE_TEMPLATE, employeeId)));

        if (updatedEmployee.getPassword().equals("")) {
            updatedEmployee.setPassword(employee.getPassword());
        }

        if ((employee.getStorage() == null || employee.getStorage().getId() != updatedEmployee.getStorageId()) && updatedEmployee.getStorageId() != null) {
            UUID storageId = updatedEmployee.getStorageId();
            Storage storage = storageRepository.findByIdAndOwnerEmail(storageId, ownerEmail)
                    .orElseThrow(() -> new NotFoundException(String.format(STORAGE_NOT_FOUND_MESSAGE_TEMPLATE, storageId)));
            employee.setStorage(storage);
        } else if (updatedEmployee.getStorageId() == null ) {
            employee.setStorage(null);
        }

        employeeMapper.updateEntity(updatedEmployee, employee);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    private String generateShortId() {
        String generatedID;
        do {
            generatedID = ShortID.randomShortID();
        } while (employeeRepository.existsByShortId(generatedID));

        return generatedID;
    }
}
