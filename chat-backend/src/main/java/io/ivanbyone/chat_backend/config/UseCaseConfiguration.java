package io.ivanbyone.chat_backend.config;

import io.ivanbyone.chat_backend.application.mapper.IssueMapper;
import io.ivanbyone.chat_backend.application.use_case.IssueUseCase;
import io.ivanbyone.chat_backend.domain.issue.IssueRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public IssueUseCase issueUseCase(IssueRepository repository, IssueMapper mapper) {
        return new IssueUseCase(repository, mapper);
    }
}
