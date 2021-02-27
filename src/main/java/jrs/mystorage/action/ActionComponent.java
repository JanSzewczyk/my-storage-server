package jrs.mystorage.action;

import jrs.mystorage.action.dto.ActionDto;
import jrs.mystorage.action.model.Action;
import jrs.mystorage.action.service.ActionService;
import jrs.mystorage.user.service.UserService;
import jrs.mystorage.util.mapper.ActionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ActionComponent {
    private final PagedResourcesAssembler<Action> actionPagedResourcesAssembler;
    private final ActionService actionService;
    private final UserService userService;
    private final ActionMapper actionMapper;

    public PagedModel<ActionDto> findAllEmployeeActions(UUID employeeId, String ownerEmail, Pageable pageable) {
        Page<Action> allEmployeeActions = actionService.getAllEmployeeActions(ownerEmail, employeeId, pageable);
        return actionPagedResourcesAssembler.toModel(allEmployeeActions, actionMapper::toDto);
    }

}
