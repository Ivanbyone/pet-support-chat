package io.ivanbyone.chat_backend.domain.user;

import io.ivanbyone.chat_backend.domain.exception.DomainValidationException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public final class User {
    private Long id;
    private String username;
    private String password;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long version;

    public User() {

    }

    public static User create(UserFields fields) {
        return new User()
                .validateAndSetUsername(fields.username())
                .validateAndSetPassword(fields.password())
                .validateAndSetRole(fields.role());
    }

    private User validateAndSetUsername(String username) {
        Optional.ofNullable(username)
                .ifPresentOrElse(
                        value -> {
                            if (value.isBlank() || value.length() < 4 || value.length() > 30) {
                                throw new DomainValidationException(
                                        "\"Username\" should be not blank and in the range of 4 to 30 characters."
                                );
                            }
                            this.username = value;
                        },
                        () -> { throw new DomainValidationException("\"Username\" is required."); }
                );
        return this;
    }

    private User validateAndSetRole(UserRole role) {
        Optional.ofNullable(role)
                .ifPresentOrElse(
                        value -> this.role = value,
                        () -> this.role = UserRole.CLIENT
                );
        return this;
    }

    private User validateAndSetPassword(String password) {
        Optional.ofNullable(password)
                .ifPresentOrElse(
                        value -> this.password = value,
                        () -> { throw new DomainValidationException("\"Password\" is required."); }
                );
        return this;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    // Equals & ToString


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", version=" + version +
                '}';
    }
}
