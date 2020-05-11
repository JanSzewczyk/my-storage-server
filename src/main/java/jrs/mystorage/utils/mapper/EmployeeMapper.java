package jrs.mystorage.utils.mapper;

import jrs.mystorage.employee.dto.CEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import jrs.mystorage.employee.dto.UEmployeeDto;
import jrs.mystorage.employee.model.Employee;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class EmployeeMapper extends Mapper<Employee, EmployeeDto>  {

    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        mapper.typeMap(UEmployeeDto.class, Employee.class).addMappings(m -> {
            m.skip(Employee::setEmployeeId);
        });
    }

    @Override
    public Employee toEntity(EmployeeDto employeeDto) {
        return mapper.map(employeeDto, Employee.class);
    }

    public Employee toEntity(CEmployeeDto newEmployeeDto) {
        Employee employee = mapper.map(newEmployeeDto, Employee.class);
        employee.setPassword(passwordEncoder.encode(newEmployeeDto.getPassword()));
        return employee;
    }

    public Employee updateEntity(UEmployeeDto updatedEmployee, Employee employee) {
        mapper.map(updatedEmployee, employee);
        return employee;
    }

    @Override
    public EmployeeDto toDto(Employee employee) {
        return mapper.map(employee, EmployeeDto.class);
    }
}
