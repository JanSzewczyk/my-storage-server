package jrs.mystorage.employee.controller;

import jrs.mystorage.employee.dto.*;
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

    @GetMapping
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<PagedModel<EmployeeViewDto>> getEmployees(
            final Principal principal,
            Pageable pageable
    ) {
        PagedModel<EmployeeViewDto> employeesByOwnerEmail = employeeService.getEmployeesByOwnerEmail(principal.getName(), pageable);
        return new ResponseEntity<>(employeesByOwnerEmail, HttpStatus.OK);
    }

    @GetMapping("/storage/{storageId}")
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<PagedModel<EmployeeViewDto>> getEmployeesWorkingInStorage(
            final Principal principal,
            Pageable pageable,
            @PathVariable UUID storageId) {
        PagedModel<EmployeeViewDto> employeesByOwnerEmail = employeeService.getEmployeesByStorage(principal.getName(),storageId, pageable);
        return new ResponseEntity<>(employeesByOwnerEmail, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<EmployeeDto> getEmployee(
            final Principal principal,
            @PathVariable UUID employeeId
    ) {
        EmployeeDto employee = employeeService.getEmployees(principal.getName(), employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<EmployeeDto> createNewEmployee(
            @RequestBody @Valid CEmployeeDto employeeDto,
            final Principal principal
    ) {
        EmployeeDto employee = employeeService.createEmployee(principal.getName(), employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PostMapping("/{employeeId}/storage")
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<EmployeeDto> changeEmployeeStorage(
            final Principal principal,
            @PathVariable UUID employeeId,
            @RequestBody @Valid AssignStorageDto assignStorage
    ) {
        EmployeeDto employee = employeeService.changeEmployeeStorage(principal.getName(), employeeId, assignStorage);
        return new ResponseEntity<>(employee, HttpStatus.OK);
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
