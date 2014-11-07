package by.tasktracker.core.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import by.tasktracker.core.models.User;

public class RolesDaoTest {

	private RolesDao rolesDao;
	
	public RolesDaoTest() {
		rolesDao = RolesDao.getRolesDao();
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getAllRolesTest() {
		assertTrue(rolesDao.getAllRoles().size() > 0);
	}
	
	@Test
	public void getUserRolesTest() {
		User user = new User();
		user.setId(1);
		assertTrue(rolesDao.getUserRoles(user).size() > 0);
	}

}
