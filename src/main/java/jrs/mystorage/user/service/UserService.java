package jrs.mystorage.user.service;

import jrs.mystorage.auth.model.Role;
import jrs.mystorage.user.dto.UserDetailsDto;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findAuthUserByEmail(String email);

    UserDetailsDto getUserDetails(String userEmail);

    Role getUserTypByEmail(String userEmail);
}