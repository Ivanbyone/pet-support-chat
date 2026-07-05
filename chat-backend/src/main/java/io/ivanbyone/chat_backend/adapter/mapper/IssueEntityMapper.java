package io.ivanbyone.chat_backend.adapter.mapper;

import io.ivanbyone.chat_backend.adapter.data.entity.IssueEntity;
import io.ivanbyone.chat_backend.domain.issue.Issue;
import org.springframework.stereotype.Component;

@Component
public class IssueEntityMapper {

    public IssueEntity toEntity(Issue model) {
        IssueEntity entity = new IssueEntity();
        entity.setId(model.getId());
        entity.setTitle(model.getTitle());
        entity.setDescription(model.getDescription());
        entity.setStatus(model.getStatus());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setUpdatedAt(model.getUpdatedAt());
        entity.setVersion(model.getVersion());
        return entity;
    }

    public Issue fromEntity(IssueEntity entity) {
        Issue model = new Issue();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setDescription(entity.getDescription());
        model.setStatus(entity.getStatus());
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());
        model.setVersion(entity.getVersion());
        return model;
    }
}
