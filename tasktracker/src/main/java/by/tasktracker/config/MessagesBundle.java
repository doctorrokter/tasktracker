package by.tasktracker.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class MessagesBundle {

	private static final Logger logger = Logger.getLogger(MessagesBundle.class);
	private Properties props;
	
	public MessagesBundle() {
		props = new Properties();
		setLanguage(Lang.EN);
	}
	
	public enum Lang {
		EN, RU;		
	}
	
	public String message(String name) {
		return props.getProperty(name);
	}
	
	public void setLanguage(Lang lang) {
		logger.info("Loading messages file: messages_" + lang + ".properties");
		try {
			props.load(MessagesBundle.class.getClassLoader().getResourceAsStream("messages_" + lang + ".properties"));
			logger.info("Messages loaded!");
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	
}
