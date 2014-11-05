package by.tasktracker.config;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppConfigTest {

	@Test
	public void pTest() {
		assertTrue(AppConfig.p("jdbc.user").equals("root"));
	}

}
