package jrs.mystorage.employee.service;

import jrs.mystorage.employee.dto.CUEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    EmployeeDto createEmployee(String ownerEmail, CUEmployeeDto newEmployee);

    List<EmployeeDto> getEmployeeByOwnerEmail(String ownerEmail);

    EmployeeDto removeEmployee(String ownerEmail, UUID employeeId);

    EmployeeDto updateEmployee(UUID employeeId, CUEmployeeDto updatedEmployee);
}
