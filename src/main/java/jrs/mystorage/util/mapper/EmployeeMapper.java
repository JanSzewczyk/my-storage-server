package jrs.mystorage.util.mapper;

import jrs.mystorage.employee.dto.CEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import jrs.mystorage.employee.dto.EmployeeViewDto;
import jrs.mystorage.employee.dto.UEmployeeDto;
import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.storage.model.Storage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class EmployeeMapper extends Mapper<Employee, EmployeeDto> {

    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final StorageMapper storageMapper;

    @PostConstruct
    public void init() {
        mapper.typeMap(UEmployeeDto.class, Employee.class).addMappings(m -> {
            m.skip(Employee::setId);
        });

        mapper.typeMap(CEmployeeDto.class, Employee.class).addMappings(m -> {
            m.skip(Employee::setId);
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
        EmployeeDto map = mapper.map(employee, EmployeeDto.class);
        Storage storage = employee.getStorage();
        map.setOwnerId(employee.getOwner().getId());
        if (storage != null) {
            map.setWorkPlace(storageMapper.toDto(storage));
        }

        return map;
    }

    public EmployeeViewDto toViewDto(Employee employee) {
        EmployeeViewDto map = mapper.map(employee, EmployeeViewDto.class);
        Storage storage = employee.getStorage();
        if (storage != null) {
            map.setStorageName(storage.getName());
            map.setStorageId(storage.getId());
        }

        return map;
    }
}
