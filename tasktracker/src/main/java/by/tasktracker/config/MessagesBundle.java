package by.tasktracker.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MessagesBundle {

private static Properties props;
	
	static {
		props = new Properties();
		setLanguage(Lang.EN);
	}
	
	private MessagesBundle() {
		
	}
	
	public enum Lang {
		EN, RU;		
	}
	
	public static String message(String name) {
		return props.getProperty(name);
	}
	
	public static void setLanguage(Lang lang) {
		System.out.println("Loading messages file: messages_" + lang + ".properties");
		try {
			props.load(MessagesBundle.class.getClassLoader().getResourceAsStream("messages_" + lang + ".properties"));
			System.out.println("Messages loaded!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
