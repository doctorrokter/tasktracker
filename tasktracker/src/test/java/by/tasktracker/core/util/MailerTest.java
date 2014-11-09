package by.tasktracker.core.util;

import org.junit.After;
import org.junit.Test;

public class MailerTest {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void sendTest() {
		Mailer mailer = Mailer.getMailer();
		mailer.send("doctorrokter@gmail.com", "JUnit test", "Hello!");
	}

}
