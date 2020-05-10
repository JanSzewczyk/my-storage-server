package jrs.mystorage.user.controller;

import jrs.mystorage.user.dto.UserDetailsDto;
import jrs.mystorage.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping("/details")
    @PreAuthorize(value = "hasAuthority('OWNER') or hasAuthority('EMPLOYEE')")
    public ResponseEntity<UserDetailsDto> getUserDetails(
            final Principal principal
    ) {
        return new ResponseEntity<>(userService.getUserDetails(principal.getName()), HttpStatus.OK);
    }
}
