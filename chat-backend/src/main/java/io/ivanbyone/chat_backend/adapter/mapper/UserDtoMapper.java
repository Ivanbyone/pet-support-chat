package io.ivanbyone.chat_backend.adapter.mapper;

import io.ivanbyone.chat_backend.application.dto.input.UserInput;
import io.ivanbyone.chat_backend.application.dto.output.UserOutput;
import io.ivanbyone.chat_backend.application.mapper.UserMapper;
import io.ivanbyone.chat_backend.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper implements UserMapper {

    @Override
    public User fromDto(UserInput input, String password) {
        return User.create(input.username(), password, input.role());
    }

    @Override
    public UserOutput toDto(User model) {
        return new UserOutput(
                model.getId(),
                model.getUsername(),
                model.getRole(),
                model.getCreatedAt()
        );
    }
}
