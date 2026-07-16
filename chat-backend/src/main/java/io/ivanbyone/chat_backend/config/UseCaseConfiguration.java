package io.ivanbyone.chat_backend.config;

import io.ivanbyone.chat_backend.application.mapper.IssueMapper;
import io.ivanbyone.chat_backend.application.mapper.UserMapper;
import io.ivanbyone.chat_backend.application.use_case.IssueUseCase;
import io.ivanbyone.chat_backend.application.use_case.UserUseCase;
import io.ivanbyone.chat_backend.domain.issue.IssueRepository;
import io.ivanbyone.chat_backend.domain.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public IssueUseCase issueUseCase(IssueRepository repository, IssueMapper mapper) {
        return new IssueUseCase(repository, mapper);
    }

    @Bean
    public UserUseCase userUseCase(UserRepository repository, UserMapper mapper) {
        return new UserUseCase(repository, mapper);
    }
}
