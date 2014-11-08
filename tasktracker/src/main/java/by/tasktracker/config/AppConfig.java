package by.tasktracker.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AppConfig {

	private static final Logger logger = Logger.getLogger(AppConfig.class);
	private static Properties props;
	
	static {
		props = new Properties();
		logger.info("Loading app config...");
		try {
			props.load(AppConfig.class.getClassLoader().getResourceAsStream("app_config.properties"));
			logger.info("App config loaded!");
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	
	private AppConfig() {
		
	}
	
	public static String p(String name) {
		return props.getProperty(name);
	}
	
}
