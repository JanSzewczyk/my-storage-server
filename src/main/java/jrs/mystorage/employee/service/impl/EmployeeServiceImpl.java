package jrs.mystorage.employee.service.impl;

import jrs.mystorage.employee.dto.*;
import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.employee.model.EmployeeView;
import jrs.mystorage.employee.repository.EmployeeRepository;
import jrs.mystorage.employee.repository.EmployeeViewRepository;
import jrs.mystorage.employee.service.EmployeeService;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.repository.OwnerRepository;
import jrs.mystorage.storage.model.Storage;
import jrs.mystorage.storage.repository.StorageRepository;
import jrs.mystorage.util.ShortID;
import jrs.mystorage.util.exception.ConflictException;
import jrs.mystorage.util.exception.NotFoundException;
import jrs.mystorage.util.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static jrs.mystorage.util.MessageTemplates.*;
import static jrs.mystorage.util.database.SpecificationBuildHelper.containsTextInAttributes;
import static jrs.mystorage.util.database.SpecificationBuildHelper.fieldEquals;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final List<String> EMPLOYEE_SEARCH_FIELDS = List.of("shortId", "email", "name", "storageName");
    private final PagedResourcesAssembler<Employee> employeePagedResourcesAssembler;
    private final EmployeeRepository employeeRepository;
    private final OwnerRepository ownerRepository;
    private final EmployeeMapper employeeMapper;
    private final StorageRepository storageRepository;
    private final EmployeeViewRepository employeeViewRepository;

    @Override
    public EmployeeDto createEmployee(String ownerEmail, CEmployeeDto newEmployee) {
        Owner owner = ownerRepository.findByEmail(ownerEmail)
                .orElseThrow(NotFoundException::new);

        Employee employee = employeeMapper.toEntity(newEmployee);
        employee.setOwner(owner);
        employee.setShortId(this.generateShortId());

        if (newEmployee.getStorageId() != null) {
            UUID storageId = newEmployee.getStorageId();
            Storage storage = storageRepository.findByIdAndOwnerEmail(storageId, ownerEmail)
                    .orElseThrow(() -> new NotFoundException(String.format(STORAGE_NOT_FOUND_MESSAGE_TEMPLATE, storageId)));

            employee.setStorage(storage);
        }

        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public Page<EmployeeView> findAllEmployeesByOwnerEmail(String ownerEmail, Pageable pageable, String search) {
//        Page<Employee> employees =
        Specification<EmployeeView> employeeViewSpecification = getEmployeeViewSpecification(search);
//        Specification<Employee> ownerEmailSpec= fieldEquals("ownerEmail", ownerEmail);
        return employeeViewRepository.findAll(employeeViewSpecification, pageable);
    }

    @Override
    public Page<EmployeeView> findAllEmployeesWorkingInStorage(String ownerEmail, UUID storageId, Pageable pageable) {
//        Specification<EmployeeView> employeeViewSpecification = getEmployeeViewSpecification(search);
        Specification<EmployeeView> ownerEmailSpec= fieldEquals("ownerEmail", ownerEmail);
        Specification<EmployeeView> storageSpec= fieldEquals("storageId", storageId);
        return employeeViewRepository.findAll(ownerEmailSpec.and(storageSpec), pageable);
    }

    @Override
    public EmployeeDto getEmployees(String ownerEmail, UUID employeeId) {
        Employee employee = employeeRepository.findByIdAndOwnerEmail(employeeId, ownerEmail)
                .orElseThrow(() -> new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_MESSAGE_TEMPLATE, employeeId)));
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto removeEmployee(String ownerEmail, UUID employeeId) {
        Employee employee = employeeRepository.findByIdAndOwnerEmail(employeeId, ownerEmail)
                .orElseThrow(() -> new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_MESSAGE_TEMPLATE, employeeId)));
        employeeRepository.delete(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto changeEmployeeStorage(String ownerEmail, UUID employeeId, AssignStorageDto assignStorage) {
        Employee employee = employeeRepository.findByIdAndOwnerEmail(employeeId, ownerEmail)
                .orElseThrow(() -> new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_MESSAGE_TEMPLATE, employeeId)));

        if ((employee.getStorage() != null && !employee.getStorage().getId().equals(assignStorage.getStorageId())) ||
                (employee.getStorage() == null && assignStorage.getStorageId() != null)) {

            if (assignStorage.getStorageId() != null) {
                UUID storageId = assignStorage.getStorageId();
                Storage newStorage = storageRepository.findByIdAndOwnerEmail(storageId, ownerEmail)
                        .orElseThrow(() -> new NotFoundException(String.format(STORAGE_NOT_FOUND_MESSAGE_TEMPLATE, storageId)));
                employee.setStorage(newStorage);
            } else {
                employee.setStorage(null);
            }

        } else throw new ConflictException(String.format(EMPLOYEE_CHANGE_STORAGE_CONFLICT_MESSAGE_TEMPLATE, employeeId));

      return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto updateEmployee(String ownerEmail, UUID employeeId, UEmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_MESSAGE_TEMPLATE, employeeId)));

        if (updatedEmployee.getPassword().equals("")) {
            updatedEmployee.setPassword(employee.getPassword());
        }

        if ((employee.getStorage() == null || employee.getStorage().getId() != updatedEmployee.getStorageId()) && updatedEmployee.getStorageId() != null) {
            UUID storageId = updatedEmployee.getStorageId();
            Storage storage = storageRepository.findByIdAndOwnerEmail(storageId, ownerEmail)
                    .orElseThrow(() -> new NotFoundException(String.format(STORAGE_NOT_FOUND_MESSAGE_TEMPLATE, storageId)));
            employee.setStorage(storage);
        } else if (updatedEmployee.getStorageId() == null ) {
            employee.setStorage(null);
        }

        employeeMapper.updateEntity(updatedEmployee, employee);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    private String generateShortId() {
        String generatedID;
        do {
            generatedID = ShortID.randomShortID();
        } while (employeeRepository.existsByShortId(generatedID));

        return generatedID;
    }

    private Specification<EmployeeView> getEmployeeViewSpecification(String searchString) {
//        Specification<OfferView> containsStateSpecification = in(OFFER_VIEW_STATE, states);
        Specification<EmployeeView> containsTextInAttributesSpecification = containsTextInAttributes(searchString, EMPLOYEE_SEARCH_FIELDS);

//        return states != null ? containsStateSpecification.and(containsTextInAttributesSpecification) : containsTextInAttributesSpecification;
        return containsTextInAttributesSpecification;
    }
}
