package io.ivanbyone.chat_backend.unit;

import io.ivanbyone.chat_backend.application.dto.input.IssueInput;
import io.ivanbyone.chat_backend.application.dto.input.IssueUpdate;
import io.ivanbyone.chat_backend.application.exception.AlreadyExistsException;
import io.ivanbyone.chat_backend.application.exception.NotFoundException;
import io.ivanbyone.chat_backend.application.mapper.IssueMapper;
import io.ivanbyone.chat_backend.application.use_case.IssueUseCase;
import io.ivanbyone.chat_backend.domain.exception.DomainBusinessException;
import io.ivanbyone.chat_backend.domain.issue.Issue;
import io.ivanbyone.chat_backend.domain.issue.IssueRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class IssueUseCaseTest {

    @Mock
    private IssueRepository issueRepository;
    @Mock
    private IssueMapper issueMapper;

    private IssueUseCase issueUseCase;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        MockitoAnnotations.openMocks(this);
        issueUseCase = new IssueUseCase(issueRepository, issueMapper);
    }

    @Test(testName = "Not opened Issue because of duplicated title", groups = "unit")
    public void shouldNotOpenIssueBecauseOfDuplicate() {
        // Given
        IssueInput input = new IssueInput("Duplicated", "Description");
        Issue exists = mock(Issue.class);
        when(issueRepository.findIssueByTitle(input.title())).thenReturn(Optional.of(exists));

        // When/Then
        assertThatThrownBy(() -> issueUseCase.openIssue(input))
                .isInstanceOf(AlreadyExistsException.class)
                .hasMessageContaining("Issue with title \"Duplicated\" already exists");

        verify(issueRepository, times(1)).findIssueByTitle(input.title());
        verify(issueMapper, never()).fromDto(any());
        verify(issueRepository, never()).save(any());
        verify(issueMapper, never()).toDto(any());
    }

    @Test(testName = "Not found Issue by id", groups = "unit")
    public void shouldThrowExceptionIfNotFound() {
        // Given
        Integer id = 1;
        when(issueRepository.findIssueById(id)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> issueUseCase.findIssueById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Issue with id 1 not found");

        verify(issueMapper, never()).toDto(any());
    }

    @Test(testName = "Don't update Issue if not found", groups = "unit")
    public void shouldNotUpdateIssueIfNotFound() {
        // Given
        Integer id = 1;
        IssueUpdate update = mock(IssueUpdate.class);
        when(issueRepository.findIssueById(id)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> issueUseCase.updateIssue(id, update))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Issue with id 1 not found");

        verify(issueRepository, never()).save(any());
        verify(issueMapper, never()).toDto(any());
    }

    @Test(testName = "Don't set IN_WORK status if not found", groups = "unit")
    public void shouldNotUpdateInWorkStatus() {
        // Given
        Integer id = 1;
        when(issueRepository.findIssueById(id)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> issueUseCase.inWorkIssue(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Issue with id 1 not found");

        verify(issueRepository, never()).save(any());
        verify(issueMapper, never()).toDto(any());
    }

    @Test(testName = "Don't set IN_WORK if status is CLOSED", groups = "unit")
    public void shouldNotUpdateInWorkStatusIfClosed() {
        // Given
        Integer id = 1;
        Issue model = new Issue().open().inWork().close();
        when(issueRepository.findIssueById(id)).thenReturn(Optional.of(model));

        // When/Then
        assertThatThrownBy(() -> issueUseCase.inWorkIssue(id))
                .isInstanceOf(DomainBusinessException.class)
                .hasMessageContaining("Invalid status change: CLOSED -> IN_WORK");

        verify(issueRepository, never()).save(any());
        verify(issueMapper, never()).toDto(any());
    }

    @Test(testName = "Don't set CLOSED status if not found", groups = "unit")
    public void shouldNotUpdateClosedStatus() {
        // Given
        Integer id = 1;
        IssueUpdate update = mock(IssueUpdate.class);
        when(issueRepository.findIssueById(id)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> issueUseCase.closeIssue(id, update))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Issue with id 1 not found");

        verify(issueRepository, never()).save(any());
        verify(issueMapper, never()).toDto(any());
    }

    @Test(testName = "Don't set CLOSED if status is OPENED", groups = "unit")
    public void shouldNotUpdateClosedStatusIfOpened() {
        // Given
        Integer id = 1;
        IssueUpdate update = new IssueUpdate(null, null, "Decision");
        Issue model = new Issue().open();
        when(issueRepository.findIssueById(id)).thenReturn(Optional.of(model));

        // When/Then
        assertThatThrownBy(() -> issueUseCase.closeIssue(id, update))
                .isInstanceOf(DomainBusinessException.class)
                .hasMessageContaining("Invalid status change: OPENED -> CLOSED");

        verify(issueRepository, never()).save(any());
        verify(issueMapper, never()).toDto(any());
    }
}
