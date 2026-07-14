package io.ivanbyone.chat_backend.adapter.data.repository;

import io.ivanbyone.chat_backend.adapter.data.entity.IssueEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CrudIssueRepository extends CrudRepository<IssueEntity, Integer> {

    Optional<IssueEntity> findByTitle(String title);
}
