package io.ivanbyone.chat_backend.domain.exception;

public class DomainBusinessException extends RuntimeException {
    public DomainBusinessException(String message) {
        super(message);
    }
}
