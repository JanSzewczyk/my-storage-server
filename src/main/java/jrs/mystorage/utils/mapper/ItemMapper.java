package jrs.mystorage.utils.mapper;

import jrs.mystorage.item.dto.CItemDto;
import jrs.mystorage.item.dto.ItemDto;
import jrs.mystorage.item.dto.StorageItemDto;
import jrs.mystorage.item.model.Item;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ItemMapper  extends Mapper<Item, ItemDto> {

    private final ModelMapper mapper;

    @PostConstruct
    public void init() {

        mapper.createTypeMap(Item.class, StorageItemDto.class)
                .addMappings(m ->{
                    m.map(s -> s.getProduct().getProductId(), StorageItemDto::setProductId);
                    m.map(s -> s.getProduct().getName(), StorageItemDto::setProductName);
                    m.map(s -> s.getProduct().getValue(), StorageItemDto::setProductValue);
                });
    }

    @Override
    public Item toEntity(ItemDto itemDto) {
        return mapper.map(itemDto, Item.class);
    }

    public Item toEntity(CItemDto newItem) {
        return mapper.map(newItem, Item.class);
    }

    @Override
    public ItemDto toDto(Item item) {
        return mapper.map(item, ItemDto.class);
    }

    public StorageItemDto toStorageDto(Item item) {
        StorageItemDto mappedItem = mapper.map(item, StorageItemDto.class);
        mappedItem.setTotalValue(item.getAmount() * item.getProduct().getValue());
        return mappedItem;
    }
}
