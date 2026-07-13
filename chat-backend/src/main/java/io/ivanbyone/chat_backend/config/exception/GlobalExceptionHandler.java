package io.ivanbyone.chat_backend.config.exception;

import io.ivanbyone.chat_backend.adapter.web.dto.ResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
@ControllerAdvice
@SuppressWarnings("unused")
public class GlobalExceptionHandler {

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
