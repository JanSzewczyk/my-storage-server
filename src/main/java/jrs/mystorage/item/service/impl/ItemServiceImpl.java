package jrs.mystorage.item.service.impl;

import jrs.mystorage.item.dto.StorageItemDto;
import jrs.mystorage.item.model.Item;
import jrs.mystorage.item.repository.ItemRepository;
import jrs.mystorage.item.service.ItemService;
import jrs.mystorage.utils.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final PagedResourcesAssembler<Item> itemPagedResourcesAssembler;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;


    @Override
    public PagedModel<StorageItemDto> getStorageItems(String ownerEmail, UUID storageId, Pageable pageable) {
        Page<Item> items = itemRepository
                .findAllByStorageStorageIdAndStorageOwnerEmail(storageId, ownerEmail, pageable);

        return itemPagedResourcesAssembler.toModel(items, itemMapper::toStorageDto);
    }
}
