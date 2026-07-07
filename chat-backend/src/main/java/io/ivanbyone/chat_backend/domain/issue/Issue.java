package io.ivanbyone.chat_backend.domain.issue;

import java.time.LocalDateTime;
import java.util.Optional;

public final class Issue {
    private Integer id;
    private String title;
    private String description;
    private IssueStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long version;

    public Issue() {

    }

    public Issue(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Issue open() {
        status = IssueStatus.OPENED;
        return this;
    }

    public Issue inWork() {
        status = IssueStatus.IN_WORK;
        return this;
    }

    public Issue close() {
        status = IssueStatus.CLOSED;
        return this;
    }

    public Issue update(UpdatableIssueFields update) {
        return this.updateTitle(update.title())
                .updateDescription(update.description());
    }

    private Issue updateTitle(String title) {
        Optional.ofNullable(title)
                .ifPresent(value -> this.title = value);
        return this;
    }

    private Issue updateDescription(String description) {
        Optional.ofNullable(description)
                .ifPresent(value -> this.description = value);
        return this;
    }

    // Getters & Setters

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
