package jrs.mystorage.util.mapper;

import jrs.mystorage.action.dto.ActionStorageDto;
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
                    m.map(s -> s.getAction().getStorage().getOwner().getCurrency(), ItemDto::setCurrency);
                });

        mapper.createTypeMap(Item.class, ActionStorageDto.class)
                .addMappings(m ->{
                    m.map(s -> s.getAction().getId(), ActionStorageDto::setActionId);
                    m.map(s -> s.getAction().getAction(), ActionStorageDto::setAction);
                    m.map(s -> s.getAction().getCreatedAt(), ActionStorageDto::setCreatedAt);

                    m.map(Item::getId, ActionStorageDto::setItemId);
                    m.map(Item::getAmount, ActionStorageDto::setItemAmount);
                    m.map(s -> s.getProduct().getName(), ActionStorageDto::setItemName);
                    m.map(s -> s.getProduct().getValue(), ActionStorageDto::setItemValue);

                    m.map(s -> s.getAction().getEmployee().getId(), ActionStorageDto::setEmployeeId);
                    m.map(s -> s.getAction().getEmployee().getFirstName(), ActionStorageDto::setEmployeeFirstName);
                    m.map(s -> s.getAction().getEmployee().getLastName(), ActionStorageDto::setEmployeeLastName);
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

    public ActionStorageDto toActionStorageDto(Item item) {
        ActionStorageDto mappedAction = mapper.map(item, ActionStorageDto.class);
        mappedAction.setItemTotalValue(item.getAmount() * item.getProduct().getValue());
        return mappedAction;
    }
}
