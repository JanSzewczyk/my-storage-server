package jrs.mystorage.user.service.impl;

import jrs.mystorage.auth.model.Role;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.repository.OwnerRepository;
import jrs.mystorage.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final OwnerRepository ownerRepository;

    public Optional<User> findAuthUserByEmail(String email) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Optional<User> user = Optional.empty();

        Optional<Owner> ofOwner = ownerRepository.findByEmail(email);
        if (ofOwner.isPresent()) {
            Owner owner = ofOwner.get();
            authorities.add(new SimpleGrantedAuthority(Role.OWNER.toString()));
            user = Optional.of(new User(owner.getEmail(), owner.getPassword(), authorities));
        }

        // TODO create Custom AuthUser
        // TODO add employee finding

        return user;
    }
}
