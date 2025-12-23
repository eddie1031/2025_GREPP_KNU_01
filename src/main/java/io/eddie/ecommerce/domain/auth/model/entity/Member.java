package io.eddie.ecommerce.domain.auth.model.entity;

import io.eddie.ecommerce.common.model.entity.BaseEntity;
import io.eddie.ecommerce.domain.auth.model.dto.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    private String username;
    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        updateRole(Role.MEMBER);
    }

    public void updateRole(Role role) {
        this.role = role;
    }

}
