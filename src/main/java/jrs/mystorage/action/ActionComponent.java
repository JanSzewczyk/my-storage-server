package jrs.mystorage.action;

import jrs.mystorage.action.model.Action;
import jrs.mystorage.action.service.ActionService;
import jrs.mystorage.auth.model.Role;
import jrs.mystorage.user.dto.UserDetailsDto;
import jrs.mystorage.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ActionComponent {

    private final ActionService actionService;
    private final UserService userService;

    public List<?> findAllEmployeeActions(UUID employeeId, String userEmail) {
//        this.actionsUserVerification(employeeId, userEmail);

        List<Action> allEmployeeActions = actionService.findAllActionsByEmployeeId(employeeId);
        return List.of();
    }

//    private void actionsUserVerification(UUID employeeId, String userEmail){
//        UserDetailsDto userDetails = userService.getUserDetails(userEmail);
//
//        if (userDetails.getRole() == Role.EMPLOYEE && userDetails.getUser().){
//
//        }
//    }
}
