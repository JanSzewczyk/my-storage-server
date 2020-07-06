package jrs.mystorage.item.controller;

import jrs.mystorage.item.dto.ItemDto;
import jrs.mystorage.item.model.StorageItemView;
import jrs.mystorage.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/storage/{storageId}")
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<PagedModel<EntityModel<StorageItemView>>> getStorageItemsView(
            final Principal principal,
            @PathVariable UUID storageId,
            Pageable pageable
    ) {
        PagedModel<EntityModel<StorageItemView>> storageItems = itemService.getStorageItemsView(principal.getName(), storageId, pageable);
        return new ResponseEntity<>(storageItems, HttpStatus.OK);
    }

    @GetMapping("/list/{storageId}")
    @PreAuthorize(value = "hasAuthority('EMPLOYEE')")
    public ResponseEntity<List<ItemDto>> getStorageItems(
            final Principal principal,
            @PathVariable UUID storageId
    ) {
        List<ItemDto> storageItems = itemService.getStorageItemsEmployee(principal.getName(), storageId);
        return new ResponseEntity<>(storageItems, HttpStatus.OK);
    }
}
