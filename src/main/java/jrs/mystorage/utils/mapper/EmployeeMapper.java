package jrs.mystorage.utils.mapper;

import jrs.mystorage.employee.dto.CUEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import jrs.mystorage.employee.model.Employee;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMapper extends Mapper<Employee, EmployeeDto>  {

    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Employee toEntity(EmployeeDto employeeDto) {
        return mapper.map(employeeDto, Employee.class);
    }

    public Employee toEntity(CUEmployeeDto newEmployeeDto) {
        Employee employee = mapper.map(newEmployeeDto, Employee.class);
        employee.setPassword(passwordEncoder.encode(newEmployeeDto.getPassword()));
        return employee;
    }

    public Employee updateEntity(CUEmployeeDto employeeDto, Employee employee) {
        // TODO null as password - not change password
        mapper.map(employeeDto, employee);
        return employee;
    }

    @Override
    public EmployeeDto toDto(Employee employee) {
        return mapper.map(employee, EmployeeDto.class);
    }
}
