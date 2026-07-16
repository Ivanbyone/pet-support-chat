package io.ivanbyone.chat_backend.domain.exception;

public class DomainValidationException extends RuntimeException {
  public DomainValidationException(String message) {
    super(message);
  }
}
