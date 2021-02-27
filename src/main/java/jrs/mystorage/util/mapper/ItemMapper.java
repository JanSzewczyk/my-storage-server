package jrs.mystorage.util.mapper;

import jrs.mystorage.item.dto.CItemDto;
import jrs.mystorage.item.dto.ItemDto;
import jrs.mystorage.item.model.Item;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ItemMapper  extends Mapper<Item, ItemDto> {

    private final ModelMapper mapper;

    @PostConstruct
    public void init() {

        mapper.createTypeMap(Item.class, ItemDto.class)
                .addMappings(m ->{
                    m.map(s -> s.getProduct().getId(), ItemDto::setProductId);
                    m.map(s -> s.getProduct().getName(), ItemDto::setProductName);
                    m.map(s -> s.getProduct().getValue(), ItemDto::setProductValue);
                    m.map(s -> s.getProduct().getDescription(), ItemDto::setProductDescription);
                    m.map(s -> s.getStorage().getOwner().getCurrency(), ItemDto::setCurrency);
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
        ItemDto mappedItem = mapper.map(item, ItemDto.class);
        mappedItem.setTotalValue(item.getAmount() * item.getProduct().getValue());
        return mappedItem;
    }

    public ItemDto toActionDto(Item item) {
        ItemDto mappedItem = mapper.map(item, ItemDto.class);
        mappedItem.setTotalValue(item.getAmount() * item.getProduct().getValue());
        mappedItem.setCurrency(item.getAction().getStorage().getOwner().getCurrency());
        return mappedItem;
    }
}
