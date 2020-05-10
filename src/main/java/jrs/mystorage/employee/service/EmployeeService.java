package jrs.mystorage.employee.service;

import jrs.mystorage.employee.dto.CEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import jrs.mystorage.employee.dto.UEmployeeDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.UUID;

public interface EmployeeService {

    PagedModel<EmployeeDto> getEmployeesByOwnerEmail(String ownerEmail, Pageable pageable);

    EmployeeDto getEmployees(String ownerEmail, UUID employeeId);

    EmployeeDto createEmployee(String ownerEmail, CEmployeeDto newEmployee);

    EmployeeDto updateEmployee(UUID employeeId, UEmployeeDto updatedEmployee);

    EmployeeDto removeEmployee(String ownerEmail, UUID employeeId);
}
