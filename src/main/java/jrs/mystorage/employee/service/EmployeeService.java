package jrs.mystorage.employee.service;

import jrs.mystorage.employee.dto.CreateEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(String ownerEmail, CreateEmployeeDto newEmployee);
    List<EmployeeDto> getEmployeeByOwnerEmail(String ownerEmail);
}
