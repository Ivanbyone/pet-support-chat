package io.ivanbyone.chat_backend.application.use_case;

import io.ivanbyone.chat_backend.application.dto.input.IssueInput;
import io.ivanbyone.chat_backend.application.dto.input.IssueUpdate;
import io.ivanbyone.chat_backend.application.dto.output.IssueOutput;
import io.ivanbyone.chat_backend.application.exception.AlreadyExistsException;
import io.ivanbyone.chat_backend.application.exception.NotFoundException;
import io.ivanbyone.chat_backend.application.mapper.IssueMapper;
import io.ivanbyone.chat_backend.domain.issue.Issue;
import io.ivanbyone.chat_backend.domain.issue.IssueRepository;

public class IssueUseCase {

    private final IssueRepository issueRepository;
    private final IssueMapper issueMapper;

    public IssueUseCase(IssueRepository issueRepository, IssueMapper issueMapper) {
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
    }

    public IssueOutput openIssue(IssueInput input) {
        // Check that issue is not exists by its title, else throw exception
        issueRepository.findIssueByTitle(input.title())
                .ifPresent(value -> {
                    throw new AlreadyExistsException(
                            "Issue with title \"%s\" already exists".formatted(input.title())
                    );
                });

        // Map input DTO to domain model "Issue"
        Issue model = issueMapper.fromDto(input).open();

        // Save issue in database
        Issue saved = issueRepository.save(model);

        // Map and return output DTO from domain model "Issue"
        return issueMapper.toDto(saved);
    }

    public IssueOutput findIssueById(Integer id) {
        Issue model = issueRepository.findIssueById(id)
                .orElseThrow(() -> new NotFoundException("Issue with id %s not found".formatted(id)));
        return issueMapper.toDto(model);
    }

    public IssueOutput updateIssue(Integer id, IssueUpdate input) {
        Issue model = issueRepository.findIssueById(id)
                .orElseThrow(() -> new NotFoundException("Issue with id %s not found".formatted(id)))
                .update(input);

        Issue saved = issueRepository.save(model);

        return issueMapper.toDto(saved);
    }
}
