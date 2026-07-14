package io.ivanbyone.chat_backend.domain.issue;

import java.util.Optional;

public interface IssueRepository {

    /**
     *
     * @param model
     * @return
     */
    Issue save(Issue model);

    /**
     *
     * @param id
     * @return
     */
    Optional<Issue> findIssueById(Integer id);

    Optional<Issue> findIssueByTitle(String title);
}
