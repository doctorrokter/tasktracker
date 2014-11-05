package by.tasktracker.db;

import by.tasktracker.config.AppConfig;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {

	private static Connection connection = null;
	
	static {
		try {
			System.out.println("Loading jdbc driver...");
			Class.forName(AppConfig.p("jdbc.driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Connection to database.");
			connection = DriverManager.getConnection(AppConfig.p("jdbc.url"), AppConfig.p("jdbc.user"), AppConfig.p("jdbc.password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private DBManager() {
		
	}
	
	public static Connection getConnection() {
		return connection;
	}
}
