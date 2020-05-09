package jrs.mystorage.employee.controller;

import jrs.mystorage.employee.dto.CUEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import jrs.mystorage.employee.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<List<EmployeeDto>> getEmployees(
            final Principal principal
    ) {
        List<EmployeeDto> employees = employeeService.getEmployeeByOwnerEmail(principal.getName());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<EmployeeDto> createNewEmployee(
            @RequestBody @Valid CUEmployeeDto employeeDto,
            final Principal principal
    ) {
        EmployeeDto employee = employeeService.createEmployee(principal.getName(), employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable UUID employeeId,
            @RequestBody @Valid CUEmployeeDto updatedEmployee
    ) {
        EmployeeDto employee = employeeService.updateEmployee(employeeId, updatedEmployee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<EmployeeDto> deleteEmployee(
            final Principal principal,
            @PathVariable UUID employeeId
    ) {
        EmployeeDto deletedEmployee = employeeService.removeEmployee(principal.getName(), employeeId);
        return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
    }
}
