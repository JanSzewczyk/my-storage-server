package jrs.mystorage.employee.controller;

import jrs.mystorage.employee.dto.CreateEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import jrs.mystorage.employee.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<EmployeeDto> create(@RequestBody @Valid CreateEmployeeDto employeeDto, final Principal principal) {

        EmployeeDto employee = employeeService.createEmployee(principal.getName(), employeeDto);

        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
}
