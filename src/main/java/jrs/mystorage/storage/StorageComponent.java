package jrs.mystorage.storage;

import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.service.OwnerService;
import jrs.mystorage.storage.model.StorageView;
import jrs.mystorage.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StorageComponent {

    private final PagedResourcesAssembler<StorageView> storageViewPagedResourcesAssembler;
    private final StorageService storageService;
    private final OwnerService ownerService;

    public PagedModel<EntityModel<StorageView>> findStorageView(String ownerEmail, Pageable pageable, String search) {
        Owner owner = ownerService.findOwnerByEmail(ownerEmail);
        return storageViewPagedResourcesAssembler.toModel(storageService.findAllStoragesByOwnerId(owner.getId(), pageable, search));
    }
}
