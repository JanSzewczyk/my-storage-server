package jrs.mystorage.item.service.impl;

import jrs.mystorage.item.dto.ItemDto;
import jrs.mystorage.item.model.Item;
import jrs.mystorage.item.model.StorageItemView;
import jrs.mystorage.item.repository.ItemRepository;
import jrs.mystorage.item.repository.StorageItemViewRepository;
import jrs.mystorage.item.service.ItemService;
import jrs.mystorage.util.database.SpecificationBuildHelper;
import jrs.mystorage.util.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final PagedResourcesAssembler<Item> itemPagedResourcesAssembler;
    private final ItemRepository itemRepository;

    private final PagedResourcesAssembler<StorageItemView> storageItemViewPagedResourcesAssembler;
    private final StorageItemViewRepository storageItemViewRepository;

    private final ItemMapper itemMapper;


    @Override
    public PagedModel<ItemDto> getStorageItems(String ownerEmail, UUID storageId, Pageable pageable) {
        Page<Item> items = itemRepository
                .findAllByStorageIdAndStorageOwnerEmail(storageId, ownerEmail, pageable);

        return itemPagedResourcesAssembler.toModel(items, itemMapper::toDto);
    }

    @Override
    public List<ItemDto> getStorageItemsEmployee(String name, UUID storageId) {
        List<Item> items = itemRepository.findAllByStorageId(storageId);
        return items.stream().map(itemMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PagedModel<EntityModel<StorageItemView>> getStorageItemsView(String name, UUID storageId, Pageable pageable) {
        Specification<StorageItemView> storageSpecification = SpecificationBuildHelper.fieldEquals("storageId", storageId);
        return storageItemViewPagedResourcesAssembler.toModel(storageItemViewRepository.findAll(storageSpecification, pageable));
    }

}
