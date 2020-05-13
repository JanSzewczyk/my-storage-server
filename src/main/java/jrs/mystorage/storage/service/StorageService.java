package jrs.mystorage.storage.service;

import jrs.mystorage.storage.dto.CUStorageDto;
import jrs.mystorage.storage.dto.StorageDto;
import jrs.mystorage.storage.dto.StorageViewDto;

import java.util.List;
import java.util.UUID;

public interface StorageService {

    List<StorageViewDto> getOwnerStorages(String ownerEmail);

    StorageDto getStorage(String userEmail, UUID storageId);

    StorageDto createStorage(String ownerEmail, CUStorageDto newStorage);

    StorageDto updateStorage(String ownerEmail, UUID storageId, CUStorageDto updatedStorage);

    StorageDto removeOwnerStorage(String ownerEmail, UUID storageId);
}
