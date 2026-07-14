package io.ivanbyone.chat_backend.application.mapper;

import io.ivanbyone.chat_backend.application.dto.input.IssueInput;
import io.ivanbyone.chat_backend.application.dto.output.IssueOutput;
import io.ivanbyone.chat_backend.domain.issue.Issue;

public interface IssueMapper {

    Issue fromDto(IssueInput input);

    IssueOutput toDto(Issue model);
}
