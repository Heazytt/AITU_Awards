package com.github.yakupovdev.aituawards.security.oidc.impl;

import com.github.yakupovdev.aituawards.model.dto.UserDto;
import com.github.yakupovdev.aituawards.security.oidc.CustomOidcUserService;
import com.github.yakupovdev.aituawards.security.principal.UserPrincipal;
import com.github.yakupovdev.aituawards.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOidcUserServiceImpl implements CustomOidcUserService {

    private final UserService userService;
    private final OidcUserService delegate = new OidcUserService();

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        OidcUser oidcUser = delegate.loadUser(userRequest);
        UUID tenantId = requiredUuidClaim(oidcUser, "tid");
        UUID objectId = requiredUuidClaim(oidcUser, "oid");
        String displayName = oidcUser.getFullName();
        if (!StringUtils.hasText(displayName)) {
            displayName = objectId.toString();
        }

        UserDto user;
        try {
            user = userService.getOrCreateMicrosoftUser(
                    tenantId, objectId, displayName, oidcUser.getEmail()
            );
        } catch (DisabledException exception) {
            throw new OAuth2AuthenticationException(
                    new OAuth2Error("access_denied"), "User access is disabled", exception
            );
        }

        Set<GrantedAuthority> authorities = new HashSet<>(oidcUser.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.role().name()));

        return new UserPrincipal(user.id(), user.role(), authorities, oidcUser);
    }

    private UUID requiredUuidClaim(OidcUser user, String name) {
        String value = user.getIdToken().getClaimAsString(name);
        try {
            if (!StringUtils.hasText(value)) {
                throw new IllegalArgumentException("Missing claim");
            }
            return UUID.fromString(value);
        } catch (IllegalArgumentException exception) {
            throw new OAuth2AuthenticationException(
                    new OAuth2Error("invalid_user_info_response"),
                    "Missing or invalid Microsoft identity claim: " + name,
                    exception
            );
        }
    }
}
