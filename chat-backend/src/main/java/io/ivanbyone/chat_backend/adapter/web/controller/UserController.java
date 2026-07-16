package io.ivanbyone.chat_backend.adapter.web.controller;

import io.ivanbyone.chat_backend.adapter.web.dto.ResponseDto;
import io.ivanbyone.chat_backend.application.dto.input.UserInput;
import io.ivanbyone.chat_backend.application.dto.output.UserOutput;
import io.ivanbyone.chat_backend.application.use_case.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserUseCase userUseCase;

    @Autowired
    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping("/auth/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<UserOutput> registration(@RequestBody UserInput input) {
        UserOutput output = userUseCase.registration(input);
        return ResponseDto.success(output, HttpStatus.CREATED.value());
    }

    @PostMapping("/auth/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody UserInput input) {}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<UserOutput> getUserById(@PathVariable("id") Long id) {
        UserOutput output = userUseCase.findUserById(id);
        return ResponseDto.success(output, HttpStatus.OK.value());
    }
}
