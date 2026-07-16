package io.ivanbyone.chat_backend.application;

public interface HashService {
    String hash(String password);
    boolean verify(String password, String hash);
}
