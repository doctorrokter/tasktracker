package by.tasktracker.db;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class DBManagerTest {

	@Test
	public void getConnectionTest() throws SQLException {
		Connection connection = DBManager.getConnection();
		assertTrue(connection != null);
		assertTrue(connection.getCatalog().equals("task_tracker"));
	}

}
