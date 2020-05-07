package jrs.mystorage.employee.service;

import jrs.mystorage.employee.dto.CreateEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto createEmployee(String ownerEmail, CreateEmployeeDto newEmployee);
}
