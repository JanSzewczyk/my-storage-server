package jrs.mystorage.employee.service;

import jrs.mystorage.employee.dto.CUEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.UUID;

public interface EmployeeService {

    EmployeeDto createEmployee(String ownerEmail, CUEmployeeDto newEmployee);

    PagedModel<EmployeeDto> getEmployeesByOwnerEmail(String ownerEmail, Pageable pageable);

    EmployeeDto removeEmployee(String ownerEmail, UUID employeeId);

    EmployeeDto updateEmployee(UUID employeeId, CUEmployeeDto updatedEmployee);
}
