package jrs.mystorage.employee;

import jrs.mystorage.employee.model.EmployeeView;
import jrs.mystorage.employee.service.EmployeeService;
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

    public PagedModel<EntityModel<EmployeeView>> findEmployeeView(String ownerEmail, Pageable pageable, String search) {

        return employeePagedResourcesAssembler.toModel(employeeService.findAllEmployeesByOwnerEmail(ownerEmail, pageable, search));
    }

    public PagedModel<EntityModel<EmployeeView>> findEmployeesViewByStorage(String ownerEmail, UUID storageId, Pageable pageable) {
        return employeePagedResourcesAssembler.toModel(employeeService.findAllEmployeesWorkingInStorage(ownerEmail, storageId, pageable));
    }
}
