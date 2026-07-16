package io.ivanbyone.chat_backend.domain.user;

import java.util.Optional;

public interface UserRepository {

    User save(User model);

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);
}
