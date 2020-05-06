package jrs.mystorage.user.service;

import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserService {

    public Optional<User> findAuthUserByEmail(String email);
}