package jrs.mystorage.action.service;

import jrs.mystorage.action.dto.ActionStorageDto;
import jrs.mystorage.action.dto.RemoveActionItemDto;
import jrs.mystorage.item.dto.CItemDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.ArrayList;
import java.util.UUID;

public interface ActionService {

    void storeItemsInStorage(String employeeEmail, ArrayList<CItemDto> newItems);

    PagedModel<ActionStorageDto> getAllStorageActions(String ownerEmail, UUID storageId, Pageable pageable);

    void removeItemsFromStorage(String employeeEmail, ArrayList<RemoveActionItemDto> removedItems);
}
