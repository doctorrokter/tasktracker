package by.tasktracker.config;

import static org.junit.Assert.*;

import org.junit.Test;

import by.tasktracker.config.MessagesBundle.Lang;

public class MessagesBundleTest {

	@Test
	public void setLanguageTest() {
		MessagesBundle mb = new MessagesBundle();
		assertTrue(mb.message("hello").equals("Hello"));
		mb.setLanguage(Lang.RU);
		assertTrue(!mb.message("hello").equals("Hello"));
	}

}
