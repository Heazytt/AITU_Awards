package com.github.yakupovdev.aituawards.security.principal;

import com.github.yakupovdev.aituawards.model.enums.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.UUID;

@Getter
public class UserPrincipal extends DefaultOidcUser {

    private static final long serialVersionUID = 1L;

    private final UUID userId;
    private final Role role;

    public UserPrincipal(UUID userId, Role role,
                         Collection<? extends GrantedAuthority> authorities,
                         OidcUser oidcUser) {
        super(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
        this.userId = userId;
        this.role = role;
    }
}
