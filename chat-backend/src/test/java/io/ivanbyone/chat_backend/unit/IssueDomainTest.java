package io.ivanbyone.chat_backend.unit;

import io.ivanbyone.chat_backend.application.dto.input.IssueUpdate;
import io.ivanbyone.chat_backend.domain.exception.DomainBusinessException;
import io.ivanbyone.chat_backend.domain.issue.Issue;
import io.ivanbyone.chat_backend.domain.issue.IssueStatus;
import io.ivanbyone.chat_backend.domain.issue.UpdatableIssueFields;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IssueDomainTest {

    private Issue model;

    @BeforeMethod(alwaysRun = true)
    public void setupModel() {
        this.model = new Issue();
    }

    @DataProvider
    public Object[][] validTitleVars() {
        String maxValidTitle = "a".repeat(255);

        return new Object[][] {
                { "Test", "Test" },
                { "Issue", "Issue" },
                { "Test Issue", "Test Issue" },
                { maxValidTitle, maxValidTitle }
        };
    }

    @DataProvider
    public Object[][] validDescriptionVars() {
        return new Object[][] {
                { "Test Description", "Test Description" },
                { "A", "A" }
        };
    }

    @Test(testName = "Set correct status when anyone opens new issue", groups = "unit")
    public void shouldCorrectOpenIssue() {
        // Business process: -> OPENED
        Issue result = model.open();

        assertThat(result.getStatus())
                .isInstanceOf(IssueStatus.class)
                .isEqualTo(IssueStatus.OPENED);
    }

    @Test(testName = "Set correct status when anyone takes issue in work", groups = "unit")
    public void shouldCorrectInWorkIssue() {
        // Business process: -> OPENED -> IN_WORK
        Issue result = model.open().inWork();

        assertThat(result.getStatus())
                .isInstanceOf(IssueStatus.class)
                .isEqualTo(IssueStatus.IN_WORK);
    }

    @Test(testName = "Set correct status when anyone closes issue", groups = "unit")
    public void shouldCorrectCloseIssue() {
        // Business process: -> OPENED -> IN_WORK -> CLOSED
        Issue result = model.open().inWork().close();

        assertThat(result.getStatus())
                .isInstanceOf(IssueStatus.class)
                .isEqualTo(IssueStatus.CLOSED);
    }

    @Test(testName = "Set correct status when anyone reopen issue", groups = "unit")
    public void shouldCorrectReopenIssue() {
        // Business process: -> OPENED -> IN_WORK -> CLOSED
        Issue result = model.open().inWork().close()
                // Reopen issue: CLOSED -> OPENED
                .open();

        assertThat(result.getStatus())
                .isInstanceOf(IssueStatus.class)
                .isEqualTo(IssueStatus.OPENED);
    }

    @Test(testName = "Throws exception when anyone set status change: CLOSED -> IN_WORK", groups = "unit")
    public void shouldThrowsExceptionForStatusChangeClosedToInWork() {
        // Business process: -> OPENED -> IN_WORK -> CLOSED
        Issue result = model.open().inWork().close();

        assertThatThrownBy(result::inWork)
                .isInstanceOf(DomainBusinessException.class);
    }

    @Test(testName = "Throws exception when anyone set status change: IN_WORK -> OPENED", groups = "unit")
    public void shouldThrowsExceptionForStatusChangeInWorkToOpened() {
        // Business process: -> OPENED -> IN_WORK
        Issue result = model.open().inWork();

        assertThatThrownBy(result::open)
                .isInstanceOf(DomainBusinessException.class)
                .hasMessageContaining("Invalid status change: IN_WORK -> OPENED");
    }

    @Test(testName = "Throws exception when anyone set status change: OPENED -> CLOSED", groups = "unit")
    public void shouldThrowsExceptionForStatusChangeOpenedToClosed() {
        // Business process: -> OPENED
        Issue result = model.open();

        assertThatThrownBy(result::close)
                .isInstanceOf(DomainBusinessException.class)
                .hasMessageContaining("Invalid status change: OPENED -> CLOSED");
    }

    @Test(testName = "Throws exception when anyone set status change: OPENED -> OPENED", groups = "unit")
    public void shouldThrowsExceptionForStatusChangeOpenedToOpened() {
        // Business process: -> OPENED
        Issue opened = model.open();

        assertThatThrownBy(opened::open)
                .isInstanceOf(DomainBusinessException.class)
                .hasMessageContaining("Invalid status change: OPENED -> OPENED");
    }

    @Test(testName = "Throws exception when anyone set status change: IN_WORK -> IN_WORK", groups = "unit")
    public void shouldThrowsExceptionForStatusChangeInWorkToInWork() {
        // Business process: -> OPENED -> IN_WORK
        Issue inWork = model.open().inWork();

        assertThatThrownBy(inWork::inWork)
                .isInstanceOf(DomainBusinessException.class)
                .hasMessageContaining("Invalid status change: IN_WORK -> IN_WORK");
    }

    @Test(testName = "Throws exception when anyone set status change: CLOSED -> CLOSED", groups = "unit")
    public void shouldThrowsExceptionForStatusChangeClosedToClosed() {
        // Business process: -> OPENED -> IN_WORK -> CLOSED
        Issue closed = model.open().inWork().close();

        assertThatThrownBy(closed::close)
                .isInstanceOf(DomainBusinessException.class)
                .hasMessageContaining("Invalid status change: CLOSED -> CLOSED");
    }

    @Test(testName = "Valid update title", groups = "unit", dataProvider = "validTitleVars")
    public void shouldUpdateValidTitle(String provided, String expected) {
        UpdatableIssueFields data = new IssueUpdate(provided, null);
        Issue result = model.update(data);

        assertThat(result.getTitle()).isEqualTo(expected);
    }

    @Test(testName = "Valid update description", groups = "unit", dataProvider = "validDescriptionVars")
    public void shouldUpdateValidDescription(String provided, String expected) {
        UpdatableIssueFields data = new IssueUpdate(null, provided);
        Issue result = model.update(data);

        assertThat(result.getDescription()).isEqualTo(expected);
    }
}
