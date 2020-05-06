package jrs.mystorage.auth.service;

import jrs.mystorage.auth.model.Role;
import jrs.mystorage.exception.NotFoundException;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService implements UserDetailsService {

    private final OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.findUserByEmail(email).orElseThrow(NotFoundException::new);
    }

    private Optional<User> findUserByEmail(String email) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Optional<User> user = Optional.empty();

        Optional<Owner> ofOwner = ownerRepository.findByEmail(email);
        if (ofOwner.isPresent()) {
            Owner owner = ofOwner.get();
            authorities.add(new SimpleGrantedAuthority(Role.OWNER.toString()));
            user = Optional.of(new User(owner.getEmail(), owner.getPassword(), authorities));
        }

        // TODO add employee finding

        return user;
    }
}
