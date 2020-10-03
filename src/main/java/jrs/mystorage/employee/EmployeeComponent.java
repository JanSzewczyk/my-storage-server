package jrs.mystorage.employee;

import jrs.mystorage.employee.model.EmployeeView;
import jrs.mystorage.employee.service.EmployeeService;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeeComponent {

    private final PagedResourcesAssembler<EmployeeView> employeePagedResourcesAssembler;
    private final EmployeeService employeeService;
    private final OwnerService ownerService;

    public PagedModel<EntityModel<EmployeeView>> findEmployeeView(String ownerEmail, Pageable pageable, String search) {
        Owner owner = ownerService.findOwnerByEmail(ownerEmail);
        return employeePagedResourcesAssembler.toModel(employeeService.findAllEmployeesByOwnerId(owner.getId(), pageable, search));
    }

    public PagedModel<EntityModel<EmployeeView>> findEmployeesViewByStorage(String ownerEmail, UUID storageId, Pageable pageable) {
        Owner owner = ownerService.findOwnerByEmail(ownerEmail);
        return employeePagedResourcesAssembler.toModel(employeeService.findAllEmployeesWorkingInStorage(owner.getId(), storageId, pageable));
    }
}
