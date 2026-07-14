package io.ivanbyone.chat_backend.adapter.mapper;

import io.ivanbyone.chat_backend.application.dto.input.IssueInput;
import io.ivanbyone.chat_backend.application.dto.output.IssueOutput;
import io.ivanbyone.chat_backend.application.mapper.IssueMapper;
import io.ivanbyone.chat_backend.domain.issue.Issue;
import org.springframework.stereotype.Component;

@Component
public class IssueDtoMapper implements IssueMapper {

    public Issue fromDto(IssueInput input) {
        Issue model = new Issue();
        model.setTitle(input.title());
        model.setDescription(input.description());
        return model;
    }

    public IssueOutput toDto(Issue model) {
        return new IssueOutput(
                model.getId(),
                model.getTitle(),
                model.getDescription(),
                model.getStatus(),
                model.getDecision(),
                model.getCreatedAt(),
                model.getUpdatedAt(),
                model.getVersion()
        );
    }
}
