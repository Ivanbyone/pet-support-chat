package io.ivanbyone.chat_backend.adapter.data.repository.impl;

import io.ivanbyone.chat_backend.adapter.data.entity.IssueEntity;
import io.ivanbyone.chat_backend.adapter.data.repository.CrudIssueRepository;
import io.ivanbyone.chat_backend.adapter.mapper.IssueEntityMapper;
import io.ivanbyone.chat_backend.domain.issue.Issue;
import io.ivanbyone.chat_backend.domain.issue.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class IssueRepositoryImpl implements IssueRepository {

    private final CrudIssueRepository repository;
    private final IssueEntityMapper mapper;

    @Autowired
    public IssueRepositoryImpl(CrudIssueRepository repository, IssueEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Issue save(Issue model) {
        IssueEntity entity = mapper.toEntity(model);
        IssueEntity saved = repository.save(entity);
        return mapper.fromEntity(saved);
    }

    @Override
    public Optional<Issue> findIssueById(Integer id) {
        return repository.findById(id).map(mapper::fromEntity);
    }

    @Override
    public Optional<Issue> findIssueByTitle(String title) {
        return repository.findByTitle(title).map(mapper::fromEntity);
    }
}
