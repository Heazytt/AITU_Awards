package com.github.yakupovdev.aituawards.repository;

import com.github.yakupovdev.aituawards.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByMicrosoftTenantIdAndMicrosoftObjectId(
            UUID microsoftTenantId, UUID microsoftObjectId
    );
}
