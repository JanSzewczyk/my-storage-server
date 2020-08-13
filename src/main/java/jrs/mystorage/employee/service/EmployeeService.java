package jrs.mystorage.employee.service;

import jrs.mystorage.employee.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.UUID;

public interface EmployeeService {

    PagedModel<EmployeeViewDto> getEmployeesByOwnerEmail(String ownerEmail, Pageable pageable);

    PagedModel<EmployeeViewDto> getEmployeesByStorage(String ownerEmail, UUID storageId, Pageable pageable);

    EmployeeDto getEmployees(String ownerEmail, UUID employeeId);

    EmployeeDto createEmployee(String ownerEmail, CEmployeeDto newEmployee);

    EmployeeDto updateEmployee(String ownerEmail, UUID employeeId, UEmployeeDto updatedEmployee);

    EmployeeDto removeEmployee(String ownerEmail, UUID employeeId);

    EmployeeDto changeEmployeeStorage(String ownerEmail, UUID employeeId, AssignStorageDto assignStorage);
}
