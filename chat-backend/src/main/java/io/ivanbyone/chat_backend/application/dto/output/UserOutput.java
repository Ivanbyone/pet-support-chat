package io.ivanbyone.chat_backend.application.dto.output;

import io.ivanbyone.chat_backend.domain.user.UserRole;

import java.time.LocalDateTime;

public record UserOutput(
        Long id,
        String username,
        UserRole role,
        LocalDateTime createdAt
) {}
