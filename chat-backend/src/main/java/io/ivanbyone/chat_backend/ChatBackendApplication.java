package io.ivanbyone.chat_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChatBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatBackendApplication.class, args);
	}

}
