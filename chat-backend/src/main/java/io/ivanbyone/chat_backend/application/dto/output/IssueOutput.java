package io.ivanbyone.chat_backend.application.dto.output;

import io.ivanbyone.chat_backend.domain.issue.IssueStatus;

import java.time.LocalDateTime;

public record IssueOutput(
        Integer id,
        String title,
        String description,
        IssueStatus status,
        String decision,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long version
) {}
