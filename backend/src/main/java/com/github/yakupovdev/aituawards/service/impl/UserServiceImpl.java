package com.github.yakupovdev.aituawards.service.impl;

import com.github.yakupovdev.aituawards.mapper.UserMapper;
import com.github.yakupovdev.aituawards.model.dto.UserDto;
import com.github.yakupovdev.aituawards.model.entity.User;
import com.github.yakupovdev.aituawards.repository.UserRepository;
import com.github.yakupovdev.aituawards.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto getOrCreateMicrosoftUser(UUID tenantId, UUID objectId,
                                            String displayName, String email) {
        User user = userRepository
                .findByMicrosoftTenantIdAndMicrosoftObjectId(tenantId, objectId)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setMicrosoftTenantId(tenantId);
                    newUser.setMicrosoftObjectId(objectId);
                    return newUser;
                });

        if (user.isBlocked() || user.isDeleted()) {
            throw new DisabledException("User access is disabled");
        }

        user.setDisplayName(displayName);
        user.setEmail(email);

        return userMapper.toDto(userRepository.save(user));
    }
}
