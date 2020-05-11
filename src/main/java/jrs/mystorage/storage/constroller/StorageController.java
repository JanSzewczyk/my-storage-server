package jrs.mystorage.storage.constroller;

import jrs.mystorage.storage.dto.CUStorageDto;
import jrs.mystorage.storage.dto.StorageDto;
import jrs.mystorage.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("storages")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @GetMapping
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<PagedModel<StorageDto>> getOwnerStorages(
            final Principal principal,
            Pageable pageable
    ) {
        PagedModel<StorageDto> ownerStorages = storageService.getOwnerStorages(principal.getName(), pageable);
        return new ResponseEntity<>(ownerStorages, HttpStatus.OK);
    }

    @GetMapping("/{storageId}")
    @PreAuthorize(value = "hasAuthority('OWNER') or hasAuthority('EMPLOYEE')")
    public ResponseEntity<StorageDto> getStorage(
            final Principal principal,
            @PathVariable UUID storageId
    ) {
        StorageDto storage = storageService.getStorage(principal.getName(), storageId);
        return new ResponseEntity<>(storage, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<StorageDto> createStorage(
            final Principal principal,
            @RequestBody @Valid CUStorageDto newStorage
    ) {
        StorageDto storage = storageService.createStorage(principal.getName(), newStorage);
        return new ResponseEntity<>(storage, HttpStatus.CREATED);
    }

    @PutMapping("/{storageId}")
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<StorageDto> updateStorage(
            final Principal principal,
            @PathVariable UUID storageId,
            @RequestBody @Valid CUStorageDto updatedStorage
    ) {
        StorageDto storageDto = storageService.updateStorage(principal.getName(), storageId, updatedStorage);
        return new ResponseEntity<>(storageDto, HttpStatus.OK);
    }

    @DeleteMapping("/{storageId}")
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<StorageDto> removeStorage(
            final Principal principal,
            @PathVariable UUID storageId
    ) {
        StorageDto storageDto = storageService.removeOwnerStorage(principal.getName(), storageId);
        return new ResponseEntity<>(storageDto, HttpStatus.OK);
    }
}
