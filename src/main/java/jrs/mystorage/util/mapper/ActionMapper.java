package jrs.mystorage.util.mapper;

import jrs.mystorage.action.dto.ActionDto;
import jrs.mystorage.action.dto.ActionStorageDto;
import jrs.mystorage.action.model.Action;
import jrs.mystorage.item.dto.ItemDto;
import jrs.mystorage.item.model.Item;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ActionMapper extends Mapper<Action, ActionDto> {

    private final ModelMapper mapper;
    private final ItemMapper itemMapper;

    @PostConstruct
    public void init() {

        mapper.createTypeMap(Action.class, ActionDto.class)
                .addMappings(m ->{
                    m.map(s -> s.getEmployee().getId(), ActionDto::setEmployeeId);
                    m.map(s -> s.getEmployee().getFirstName(), ActionDto::setEmployeeFirstName);
                    m.map(s -> s.getEmployee().getLastName(), ActionDto::setEmployeeLastName);
                    m.map(s -> s.getStorage().getOwner().getCurrency(), ActionDto::setCurrency);
                });

        mapper.createTypeMap(Action.class, ActionStorageDto.class)
               .addMappings(m -> {
                  m.map(s -> s.getEmployee().getId(), ActionStorageDto::setEmployeeId);
                  m.map(s -> s.getEmployee().getFirstName(), ActionStorageDto::setEmployeeFirstName);
                  m.map(s -> s.getEmployee().getLastName(), ActionStorageDto::setEmployeeLastName);
               });
    }

    @Override
    public Action toEntity(ActionDto actionDto) {
        return null;
    }

    @Override
    public ActionDto toDto(Action action) {
        ActionDto mappedAction = mapper.map(action, ActionDto.class);
        mappedAction.setItems(
                action.getItems()
                        .stream()
                        .map(itemMapper::toDto)
                        .collect(Collectors.toList())
        );

        return mappedAction;
    }
}
