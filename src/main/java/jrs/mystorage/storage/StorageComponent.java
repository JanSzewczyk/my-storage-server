package jrs.mystorage.storage;

import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.service.OwnerService;
import jrs.mystorage.storage.dto.StorageDto;
import jrs.mystorage.storage.model.Storage;
import jrs.mystorage.storage.model.StorageView;
import jrs.mystorage.storage.service.StorageService;
import jrs.mystorage.util.mapper.StorageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StorageComponent {

    private final StorageMapper storageMapper;
    private final PagedResourcesAssembler<StorageView> storageViewPagedResourcesAssembler;
    private final StorageService storageService;
    private final OwnerService ownerService;

    public PagedModel<EntityModel<StorageView>> findStorageView(String ownerEmail, Pageable pageable, String search) {
        Owner owner = ownerService.findOwnerByEmail(ownerEmail);
        return storageViewPagedResourcesAssembler.toModel(storageService.findAllStoragesByOwnerId(owner.getId(), pageable, search));
    }

    public List<StorageDto> findAllStorages(String ownerEmail) {
        Owner owner = ownerService.findOwnerByEmail(ownerEmail);
        List<Storage> allStorages = storageService.findAllStorages(owner.getId());
        return allStorages
                .stream()
                .map(storageMapper::toDto)
                .collect(Collectors.toList());
    }
}
