package jrs.mystorage.employee.service;

import jrs.mystorage.employee.dto.*;
import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.employee.model.EmployeeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.UUID;

public interface EmployeeService {

    Page<EmployeeView> findAllEmployeesByOwnerId(UUID ownerId, Pageable pageable, String search);

    Page<EmployeeView> findAllEmployeesWorkingInStorage(UUID ownerId, UUID storageId, Pageable pageable);

    EmployeeDto getEmployees(String ownerEmail, UUID employeeId);

    EmployeeDto createEmployee(String ownerEmail, CEmployeeDto newEmployee);

    EmployeeDto updateEmployee(String ownerEmail, UUID employeeId, UEmployeeDto updatedEmployee);

    EmployeeDto removeEmployee(String ownerEmail, UUID employeeId);

    EmployeeDto changeEmployeeStorage(String ownerEmail, UUID employeeId, AssignStorageDto assignStorage);
}
