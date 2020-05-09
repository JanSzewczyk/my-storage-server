package jrs.mystorage.employee.controller;

import jrs.mystorage.employee.dto.CreateEmployeeDto;
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
import java.util.Set;

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
    public ResponseEntity<EmployeeDto> create(
            @RequestBody @Valid CreateEmployeeDto employeeDto,
            final Principal principal
    ) {
        EmployeeDto employee = employeeService.createEmployee(principal.getName(), employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
}
