package jrs.mystorage.storage.constroller;

import jrs.mystorage.employee.dto.EmployeeDto;
import jrs.mystorage.storage.dto.CUStorageDto;
import jrs.mystorage.storage.dto.StorageDto;
import lombok.RequiredArgsConstructor;
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

    // private final StorageService storageService;

    @GetMapping
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<PagedModel<StorageDto>> getOwnerStorages(
            final Principal principal
    ) {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/{storageId}")
    @PreAuthorize(value = "hasAuthority('OWNER') or hasAuthority('EMPLOYEE')")
    public ResponseEntity<StorageDto> getStorage(
            final Principal principal,
            @PathVariable UUID storageId
    ) {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<StorageDto> createStorage(
            final Principal principal,
            @RequestBody @Valid CUStorageDto newStorage
    ) {

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping("/{storageId]")
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<StorageDto> updateStorage(
            final Principal principal,
            @PathVariable UUID storageId,
            @RequestBody @Valid CUStorageDto updatedStorage
    ) {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{storageId]")
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<StorageDto> removeStorage(
            final Principal principal,
            @PathVariable UUID storageId
    ) {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
