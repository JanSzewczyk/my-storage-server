package jrs.mystorage.auth.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

@Data
public final class AuthGrantedAuthority implements GrantedAuthority {

    private final Role role;

    public AuthGrantedAuthority(Role role) {
        Assert.notNull(role, "A granted authority textual representation is required");
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role.toString();
    }
}
