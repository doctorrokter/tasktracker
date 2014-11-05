package by.tasktracker.config;

import static org.junit.Assert.*;

import org.junit.Test;

import by.tasktracker.config.MessagesBundle.Lang;

public class MessagesBundleTest {

	@Test
	public void setLanguageTest() {
		assertTrue(MessagesBundle.message("hello").equals("Hello!"));
		MessagesBundle.setLanguage(Lang.RU);
		assertTrue(!MessagesBundle.message("hello").equals("Hello!"));
	}

}
