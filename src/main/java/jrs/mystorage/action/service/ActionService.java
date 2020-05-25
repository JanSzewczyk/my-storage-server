package jrs.mystorage.action.service;

import jrs.mystorage.action.dto.ActionStorageViewDto;
import jrs.mystorage.employee.dto.EmployeeDto;
import jrs.mystorage.item.dto.CItemDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.ArrayList;
import java.util.UUID;

public interface ActionService {

    void storeItemsInStorage(String employeeEmail, ArrayList<CItemDto> newItems);

    PagedModel<ActionStorageViewDto> getAllStorageActions(String ownerEmail, UUID storageId, Pageable pageable);
}
