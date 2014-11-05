package by.tasktracker.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {

	private static Properties props;
	
	static {
		props = new Properties();
		System.out.println("Loading app config...");
		try {
			props.load(AppConfig.class.getClassLoader().getResourceAsStream("app_config.properties"));
			System.out.println("App config loaded!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private AppConfig() {
		
	}
	
	public static String p(String name) {
		return props.getProperty(name);
	}
	
}
