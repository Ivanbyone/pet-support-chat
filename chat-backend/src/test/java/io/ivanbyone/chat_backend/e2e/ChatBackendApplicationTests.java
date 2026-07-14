package io.ivanbyone.chat_backend.e2e;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
class ChatBackendApplicationTests extends AbstractTestNGSpringContextTests {

	@Test(groups = "e2e")
	public void contextLoads() {
	}
}
