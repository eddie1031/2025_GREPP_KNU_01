package io.eddie.ecommerce.domain.auth.model.dto;

public record MemberSaveRequest(
        String username,
        String password,
        String email
) {
}
