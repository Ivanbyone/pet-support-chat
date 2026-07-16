package io.ivanbyone.chat_backend.adapter.data.repository.impl;

import io.ivanbyone.chat_backend.adapter.data.entity.UserEntity;
import io.ivanbyone.chat_backend.adapter.data.repository.CrudUserRepository;
import io.ivanbyone.chat_backend.adapter.mapper.UserEntityMapper;
import io.ivanbyone.chat_backend.domain.user.User;
import io.ivanbyone.chat_backend.domain.user.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Log4j2
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final CrudUserRepository repository;
    private final UserEntityMapper mapper;

    @Autowired
    public UserRepositoryImpl(CrudUserRepository repository, UserEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User save(User model) {
        UserEntity entity = mapper.toEntity(model);
        log.debug("Before saving: {}", entity.toString());

        UserEntity saved = repository.save(entity);
        log.debug("After saving: {}", saved.toString());

        return mapper.fromEntity(saved);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username).map(mapper::fromEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
