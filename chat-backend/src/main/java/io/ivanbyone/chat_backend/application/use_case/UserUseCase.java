package io.ivanbyone.chat_backend.application.use_case;

import io.ivanbyone.chat_backend.application.dto.input.UserInput;
import io.ivanbyone.chat_backend.application.dto.output.UserOutput;
import io.ivanbyone.chat_backend.application.exception.AlreadyExistsException;
import io.ivanbyone.chat_backend.application.exception.NotFoundException;
import io.ivanbyone.chat_backend.application.mapper.UserMapper;
import io.ivanbyone.chat_backend.domain.user.User;
import io.ivanbyone.chat_backend.domain.user.UserRepository;

public class UserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserUseCase(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserOutput registration(UserInput input) {
        userRepository.findByUsername(input.username())
                .ifPresent(value -> {
                    throw new AlreadyExistsException("User with such username already exists");
                });
        User model = userMapper.fromDto(input);
        User saved = userRepository.save(model);
        return userMapper.toDto(saved);
    }

    public void login() {

    }

    public UserOutput findUserById(Long id) {
        User model = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id %s is not found".formatted(id)));
        return userMapper.toDto(model);
    }
}
