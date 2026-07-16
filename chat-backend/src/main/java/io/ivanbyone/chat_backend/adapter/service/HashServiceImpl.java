package io.ivanbyone.chat_backend.adapter.service;

import io.ivanbyone.chat_backend.application.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashServiceImpl implements HashService {

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public HashServiceImpl(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String hash(String password) {
        return encoder.encode(password);
    }

    public boolean verify(String password, String hash) {
        return encoder.matches(password, hash);
    }
}
