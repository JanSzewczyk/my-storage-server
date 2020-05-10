package jrs.mystorage.storage.service.impl;

import jrs.mystorage.storage.dto.CUStorageDto;
import jrs.mystorage.storage.dto.StorageDto;
import jrs.mystorage.storage.service.StorageService;
import jrs.mystorage.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final UserService userService;


    @Override
    public PagedModel<StorageDto> getOwnerStorages(String ownerEmail, Pageable pageable) {
        return null;
    }

    @Override
    public StorageDto getStorage(String userEmail, UUID storageId) {
        return null;
    }

    @Override
    public StorageDto createStorage(String ownerEmail, CUStorageDto newStorage) {
        return null;
    }

    @Override
    public StorageDto updateStorage(String ownerEmail, UUID storageId, CUStorageDto updatedStorage) {
        return null;
    }

    @Override
    public StorageDto removeOwnerStorage(String ownerEmail, UUID storageId) {
        return null;
    }
}
