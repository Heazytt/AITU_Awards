package com.github.yakupovdev.aituawards.model.entity;

import com.github.yakupovdev.aituawards.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_users_microsoft_identity",
                columnNames = {"microsoft_tenant_id", "microsoft_object_id"}
        )
})
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "microsoft_tenant_id", nullable = false)
    private UUID microsoftTenantId;

    @Column(name = "microsoft_object_id", nullable = false)
    private UUID microsoftObjectId;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "email", length = 320)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 32)
    private Role role = Role.PARTICIPANT;

    @Column(name = "blocked", nullable = false)
    private boolean blocked = false;
}
