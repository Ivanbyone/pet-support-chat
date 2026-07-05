package io.ivanbyone.chat_backend.adapter.web.controller;

import io.ivanbyone.chat_backend.adapter.web.dto.ResponseDto;
import io.ivanbyone.chat_backend.application.dto.input.IssueInput;
import io.ivanbyone.chat_backend.application.dto.input.IssueUpdate;
import io.ivanbyone.chat_backend.application.dto.output.IssueOutput;
import io.ivanbyone.chat_backend.application.use_case.IssueUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/issues")
public class IssueController {

    private final IssueUseCase issueUseCase;

    @Autowired
    public IssueController(IssueUseCase issueUseCase) {
        this.issueUseCase = issueUseCase;
    }

    @GetMapping
    public void getIssues() {

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<IssueOutput> openIssue(@RequestBody IssueInput input) {
        IssueOutput output = issueUseCase.openIssue(input);
        return ResponseDto.success(output, HttpStatus.CREATED.value());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<IssueOutput> getIssueById(@PathVariable("id") Integer id) {
        IssueOutput output = issueUseCase.findIssueById(id);
        return ResponseDto.success(output, HttpStatus.OK.value());
    }

    @PatchMapping("/{id}")
    public ResponseDto<IssueOutput> updateIssue(@PathVariable("id") Integer id, @RequestBody IssueUpdate input) {
        IssueOutput output = issueUseCase.updateIssue(id, input);
        return ResponseDto.success(output, HttpStatus.OK.value());
    }
}
