package io.ivanbyone.chat_backend.config.exception;

import io.ivanbyone.chat_backend.adapter.web.dto.ResponseDto;
import io.ivanbyone.chat_backend.application.exception.AlreadyExistsException;
import io.ivanbyone.chat_backend.application.exception.NotFoundException;
import io.ivanbyone.chat_backend.domain.exception.DomainBusinessException;
import io.ivanbyone.chat_backend.domain.exception.DomainValidationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@Log4j2
@RestControllerAdvice
@SuppressWarnings("unused")
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainBusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleDomainBusinessException(DomainBusinessException exception) {
        String message = exception.getLocalizedMessage();
        log.warn(message);
        return ResponseDto.error(message, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(DomainValidationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ResponseDto<?> handleDomainValidationException(DomainValidationException exception) {
        String message = exception.getLocalizedMessage();
        log.warn(message);
        return ResponseDto.error(message, HttpStatus.UNPROCESSABLE_CONTENT.value());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<?> handleNotFoundException(NotFoundException exception) {
        String message = exception.getLocalizedMessage();
        log.warn(message);
        return ResponseDto.error(message, HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseDto<?> handleAlreadyExistsException(AlreadyExistsException exception) {
        String message = exception.getLocalizedMessage();
        log.warn(message);
        return ResponseDto.error(exception.getLocalizedMessage(), HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseDto<?> handleOptimisticLockFailureException(OptimisticLockingFailureException exception) {
        String message = "Conflict when modifying a record. Please try again later.";
        log.warn(exception.getLocalizedMessage());
        return ResponseDto.error(message, HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto<?> handleUnexpectedError(Throwable error) {
        String errorId = UUID.randomUUID().toString();
        log.error("Internal Server Error [ID: {}]", errorId, error);
        String message = "Internal Server Error [ID: %s]. Please contact the administrator.".formatted(errorId);
        return ResponseDto.error(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
