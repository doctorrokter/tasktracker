package by.tasktracker.core.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import by.tasktracker.core.models.Role;
import by.tasktracker.core.models.User;

public class UsersDaoTest extends AbstractDao {

	private UsersDao usersDao;
	
	public UsersDaoTest() {
		usersDao = UsersDao.getUsersDao();
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findUserByIdTest() {
		assertTrue(usersDao.findUserById(0) == null);
		User user = usersDao.findUserById(2);
		
		assertTrue(user.getLogin().equals("test"));
	}
	
	@Test
	public void findUserByLoginTest() {
		assertTrue(usersDao.findUserByLogin("eddie") == null);
		assertTrue(usersDao.findUserByLogin("root") != null);
	}
	
	@Test
	public void findUserByCredentialsTest() {
		assertTrue(usersDao.findUserByCredentials("eddie", "eddie") == null);
		assertTrue(usersDao.findUserByCredentials("root", "root") != null);
	}
	
	@Test
	public void getAllUsersTest() {
		assertTrue(usersDao.getAllUsers().size() > 0);
	}
	
	@Test
	public void findUsersByRoleTest() {
		Role role = new Role();
		role.setId(1);
		assertTrue(usersDao.findUsersByRole(role).size() > 0);
	}

}
