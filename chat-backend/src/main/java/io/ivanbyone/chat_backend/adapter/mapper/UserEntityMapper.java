package io.ivanbyone.chat_backend.adapter.mapper;

import io.ivanbyone.chat_backend.adapter.data.entity.UserEntity;
import io.ivanbyone.chat_backend.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public UserEntity toEntity(User model) {
        UserEntity entity = new UserEntity();
        entity.setId(model.getId());
        entity.setUsername(model.getUsername());
        entity.setPassword(model.getPassword());
        entity.setRole(model.getRole());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setUpdatedAt(model.getUpdatedAt());
        entity.setVersion(model.getVersion());
        return entity;
    }

    public User fromEntity(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setPassword(entity.getPassword());
        model.setRole(entity.getRole());
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());
        model.setVersion(entity.getVersion());
        return model;
    }
}
