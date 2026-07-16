package io.ivanbyone.chat_backend.application.dto.input;

import io.ivanbyone.chat_backend.domain.user.UserFields;
import io.ivanbyone.chat_backend.domain.user.UserRole;

public record UserInput(
        String username,
        String password,
        UserRole role
) implements UserFields {}
