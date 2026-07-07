package io.ivanbyone.chat_backend.application.dto.input;

import io.ivanbyone.chat_backend.domain.issue.UpdatableIssueFields;

public record IssueUpdate(
        String title,
        String description
) implements UpdatableIssueFields {}
