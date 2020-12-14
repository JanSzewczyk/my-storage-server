package jrs.mystorage.storage.service;

import jrs.mystorage.action.dto.RemoveActionItemDto;
import jrs.mystorage.item.model.Item;
import jrs.mystorage.storage.dto.CUStorageDto;
import jrs.mystorage.storage.dto.StorageDto;
import jrs.mystorage.storage.dto.StorageStatisticDto;
import jrs.mystorage.storage.model.Storage;
import jrs.mystorage.storage.model.StorageView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface StorageService {

    List<Storage> findAllStorages(UUID ownerId);

    StorageDto getStorage(String userEmail, UUID storageId);

    StorageDto createStorage(String ownerEmail, CUStorageDto newStorage);

    StorageDto updateStorage(String ownerEmail, UUID storageId, CUStorageDto updatedStorage);

    StorageDto removeOwnerStorage(String ownerEmail, UUID storageId);

    void storeItemsInStorage(UUID storageId, List<Item> items);

    void removeStorageItems(UUID storageId, ArrayList<RemoveActionItemDto> removedItems);

    List<StorageStatisticDto> getStorageValueStatistics(String ownerEmail, UUID storageId);

    Page<StorageView> findAllStoragesByOwnerId(UUID ownerId, Pageable pageable, String search);
}
