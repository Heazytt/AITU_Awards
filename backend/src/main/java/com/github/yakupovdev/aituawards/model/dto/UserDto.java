package com.github.yakupovdev.aituawards.model.dto;

import com.github.yakupovdev.aituawards.model.enums.Role;

import java.util.UUID;

public record UserDto(
        UUID id,
        String displayName,
        String email,
        Role role,
        boolean blocked
) {
}
