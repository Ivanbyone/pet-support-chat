package io.ivanbyone.chat_backend.application.dto.input;

import io.ivanbyone.chat_backend.domain.issue.IssueStatus;

public record IssueUpdate(
        String title,
        String description,
        IssueStatus status
) {}
