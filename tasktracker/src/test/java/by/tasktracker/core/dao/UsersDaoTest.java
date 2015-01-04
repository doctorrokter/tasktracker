package by.tasktracker.core.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import by.tasktracker.core.models.Role;
import by.tasktracker.core.models.User;
import by.tasktracker.db.DaoHelper;

public class UsersDaoTest extends AbstractDao {

	private UsersDao usersDao;
	private RolesDao rolesDao;
	
	public UsersDaoTest() {
		usersDao = UsersDao.getUsersDao();
		rolesDao = RolesDao.getRolesDao();
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findUserByIdTest() {
		assertTrue(usersDao.findUserById(0) == null);
		User user = usersDao.findUserById(1);
		
		assertTrue(user.getLogin().equals("root"));
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
	
	@Test
	public void assignRolesTest() {
		User user = DaoHelper.createUser();
		user.setId(usersDao.createUser(user));
		
		Role role1 = new Role();
		role1.setId(1);
		
		Role role2 = new Role();
		role2.setId(2);
		
		usersDao.assignRole(user, role1);
		usersDao.assignRole(user, role2);
	
		assertTrue(rolesDao.getUserRoles(user).size() == 2);
		
		usersDao.deleteUser(user);
	}

}
