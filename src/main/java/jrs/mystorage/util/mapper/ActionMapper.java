package jrs.mystorage.util.mapper;

import jrs.mystorage.action.dto.ActionDto;
import jrs.mystorage.action.dto.ActionStorageDto;
import jrs.mystorage.action.model.Action;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ActionMapper extends Mapper<Action, ActionDto> {

    private final ModelMapper mapper;

    @PostConstruct
    public void init() {
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
        return null;
    }
}
