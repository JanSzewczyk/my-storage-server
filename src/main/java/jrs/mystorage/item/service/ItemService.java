package jrs.mystorage.item.service;

import jrs.mystorage.item.dto.ItemDto;
import jrs.mystorage.item.model.StorageItemView;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.util.List;
import java.util.UUID;

public interface ItemService {

    PagedModel<ItemDto> getStorageItems(String ownerEmail, UUID storageId, Pageable pageable);

    List<ItemDto> getStorageItemsEmployee(String name, UUID storageId);

    PagedModel<EntityModel<StorageItemView>> getStorageItemsView(String name, UUID storageId, Pageable pageable);
}
