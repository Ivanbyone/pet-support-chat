package io.ivanbyone.chat_backend.config.exception;

import io.ivanbyone.chat_backend.adapter.web.dto.ResponseDto;
import io.ivanbyone.chat_backend.application.exception.AlreadyExistsException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
@SuppressWarnings("unused")
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseDto<?> handleAlreadyExistsException(AlreadyExistsException error) {
        String message = error.getLocalizedMessage();
        log.warn(message);
        return ResponseDto.error(message, HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseDto<?> handleOptimisticLockFailureException(OptimisticLockingFailureException error) {
        String message = "Conflict when modifying a record. Please try again later.";
        log.warn("{}, more information: {}", message, error.getLocalizedMessage());
        return ResponseDto.error(
                "Conflict when modifying a record. Please try again later.",
                HttpStatus.CONFLICT.value()
        );
    }
}
