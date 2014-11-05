package by.tasktracker.core.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Test;

import by.tasktracker.core.models.Task;
import by.tasktracker.db.DaoHelper;

public class AbstractDaoTest extends AbstractDao {

	@After
	public void tearDown() {
		deleteAll(Task.class);
	}
		
	@Test
	public void saveTest() throws SQLException {
		Task task = DaoHelper.createTask();
		task.setId(save(task));
		String query = "SELECT * FROM tasks WHERE id = " + task.getId();
		task = (Task) executeQuery(query, Task.class);
		assertTrue(task.getTitle().equals("Test project"));
	}
	
	@Test
	public void deleteAllTest() {
		Task task = DaoHelper.createTask();
		task.setId(save(task));
		String query = "SELECT * FROM tasks WHERE id = " + task.getId();
		task = (Task) executeQuery(query, Task.class);
		assertTrue(task.getTitle().equals("Test project"));
		
		deleteAll(Task.class);
		task = (Task) executeQuery(query, Task.class);
		assertTrue(task == null);
	}
	
	@Test
	public void findAllTest() {
		save(DaoHelper.createTask());
		save(DaoHelper.createTask());
		save(DaoHelper.createTask());
		assertTrue(findAll(Task.class).size() == 3);
	}
	
	@Test
	public void findTest() {
		Task task = DaoHelper.createTask();
		task.setId(save(DaoHelper.createTask()));
		task = (Task) find(Task.class, "WHERE id = " + task.getId());
		assertTrue(task != null);
		assertTrue(task.getTitle().equals("Test project"));
	}
}
