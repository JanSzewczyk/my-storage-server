package jrs.mystorage.utils.mapper;

import jrs.mystorage.action.dto.ActionDto;
import jrs.mystorage.action.dto.ActionStorageViewDto;
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
       mapper.createTypeMap(Action.class, ActionStorageViewDto.class)
               .addMappings(m -> {
                  m.map(s -> s.getEmployee().getEmployeeId(), ActionStorageViewDto::setEmployeeId);
                  m.map(s -> s.getEmployee().getFirstName(), ActionStorageViewDto::setEmployeeFirstName);
                  m.map(s -> s.getEmployee().getLastName(), ActionStorageViewDto::setEmployeeLastName);
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

    public ActionStorageViewDto toActionStorageViewDto(Action action) {
        return mapper.map(action, ActionStorageViewDto.class);
    }
}
