package jrs.mystorage.user.dto;

import jrs.mystorage.auth.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {

    private UserDto user;
    private Role role;
}
