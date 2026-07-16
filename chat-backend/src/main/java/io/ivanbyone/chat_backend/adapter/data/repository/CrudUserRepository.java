package io.ivanbyone.chat_backend.adapter.data.repository;

import io.ivanbyone.chat_backend.adapter.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CrudUserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
