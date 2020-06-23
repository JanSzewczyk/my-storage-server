package jrs.mystorage.auth.service;

import jrs.mystorage.util.exception.UnauthorizedException;
import jrs.mystorage.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userService.findAuthUserByEmail(email).orElseThrow(() -> new UnauthorizedException("Invalid email or password"));
    }
}
