package by.tasktracker.db;

import by.tasktracker.config.AppConfig;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DBManager {

	private static final Logger logger = Logger.getLogger(DBManager.class);
	private static DataSource dataSource;
		
	static {
		logger.info("Establishing DB connection...");
		dataSource = new DataSource();
		PoolProperties poolProps = new PoolProperties();
		poolProps.setDriverClassName(AppConfig.p("jdbc.driver"));
		poolProps.setUrl(AppConfig.p("jdbc.url"));
		poolProps.setUsername(AppConfig.p("jdbc.user"));
		poolProps.setPassword(AppConfig.p("jdbc.password"));
		poolProps.setInitialSize(Integer.parseInt(AppConfig.p("jdbc.initialSize")));
		poolProps.setMaxActive(Integer.parseInt(AppConfig.p("jdbc.maxActive")));
		poolProps.setMinIdle(Integer.parseInt(AppConfig.p("jdbc.minIdle")));
		dataSource.setPoolProperties(poolProps);
		logger.info("Connected!");
	}
	
	private DBManager() {
		
	}
	
	public static Connection getConnection() {
		Connection c = null;
		try {
			c = dataSource.getConnection();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return c;
	}
}
