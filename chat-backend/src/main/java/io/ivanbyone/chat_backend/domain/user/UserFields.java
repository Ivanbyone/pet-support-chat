package io.ivanbyone.chat_backend.domain.user;

public interface UserFields {
    String username();
    String password();
    UserRole role();
}
