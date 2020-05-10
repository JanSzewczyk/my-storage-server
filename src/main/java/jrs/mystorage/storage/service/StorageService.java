package jrs.mystorage.storage.service;

import jrs.mystorage.storage.dto.CUStorageDto;
import jrs.mystorage.storage.dto.StorageDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.UUID;

public interface StorageService {

    PagedModel<StorageDto> getOwnerStorages(String ownerEmail, Pageable pageable);

    StorageDto getStorage(String userEmail, UUID storageId);

    StorageDto createStorage(String ownerEmail, CUStorageDto newStorage);

    StorageDto updateStorage(String ownerEmail, UUID storageId, CUStorageDto updatedStorage);

    StorageDto removeOwnerStorage(String ownerEmail, UUID storageId);
}
