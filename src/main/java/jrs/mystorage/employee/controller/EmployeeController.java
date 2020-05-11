package jrs.mystorage.employee.controller;

import jrs.mystorage.employee.dto.CEmployeeDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import jrs.mystorage.employee.dto.UEmployeeDto;
import jrs.mystorage.employee.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // TODO crete assignEmployeeToStorage

    @GetMapping
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<PagedModel<EmployeeDto>> getEmployees(
            final Principal principal,
            Pageable pageable
    ) {
        PagedModel<EmployeeDto> employeesByOwnerEmail = employeeService.getEmployeesByOwnerEmail(principal.getName(), pageable);
        return new ResponseEntity<>(employeesByOwnerEmail, HttpStatus.OK);
    }

    // TODO get not assign employee

    @GetMapping("/{employeeId}")
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<EmployeeDto> getEmployee(
            final Principal principal,
            @PathVariable UUID employeeId
    ) {
        EmployeeDto employee = employeeService.getEmployees(principal.getName(), employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // TODO default storage
    @PostMapping
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<EmployeeDto> createNewEmployee(
            @RequestBody @Valid CEmployeeDto employeeDto,
            final Principal principal
    ) {
        EmployeeDto employee = employeeService.createEmployee(principal.getName(), employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<EmployeeDto> updateEmployee(
            final Principal principal,
            @PathVariable UUID employeeId,
            @RequestBody @Valid UEmployeeDto updatedEmployee
    ) {
        EmployeeDto employee = employeeService.updateEmployee(principal.getName(), employeeId, updatedEmployee);
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
