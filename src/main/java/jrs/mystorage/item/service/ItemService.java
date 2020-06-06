package jrs.mystorage.item.service;

import jrs.mystorage.item.dto.StorageItemDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.List;
import java.util.UUID;

public interface ItemService {

    PagedModel<StorageItemDto> getStorageItems(String ownerEmail, UUID storageId, Pageable pageable);

    List<StorageItemDto> getStorageItemsEmployee(String name, UUID storageId);
}
