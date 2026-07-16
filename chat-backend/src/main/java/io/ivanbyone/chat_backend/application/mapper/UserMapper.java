package io.ivanbyone.chat_backend.application.mapper;

import io.ivanbyone.chat_backend.application.dto.input.UserInput;
import io.ivanbyone.chat_backend.application.dto.output.UserOutput;
import io.ivanbyone.chat_backend.domain.user.User;

public interface UserMapper {

    User fromDto(UserInput input, String password);

    UserOutput toDto(User model);
}
