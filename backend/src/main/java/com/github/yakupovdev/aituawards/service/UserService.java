package com.github.yakupovdev.aituawards.service;

import com.github.yakupovdev.aituawards.model.dto.UserDto;

import java.util.UUID;

public interface UserService {

    UserDto getOrCreateMicrosoftUser(UUID tenantId, UUID objectId,
                                     String displayName, String email);
}
