package jrs.mystorage.action.service;

import jrs.mystorage.action.dto.ActionDto;
import jrs.mystorage.action.dto.RemoveActionItemDto;
import jrs.mystorage.action.model.Action;
import jrs.mystorage.item.dto.CItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface ActionService {

    void storeItemsInStorage(String employeeEmail, ArrayList<CItemDto> newItems);

    PagedModel<ActionDto> getAllStorageActions(String ownerEmail, UUID storageId, Pageable pageable);
    Page<Action> getAllEmployeeActions(String ownerEmail, UUID employeeId, Pageable pageable);

    void removeItemsFromStorage(String employeeEmail, ArrayList<RemoveActionItemDto> removedItems);

    List<Action> findAllActionsByEmployeeId(UUID employeeId);
}
