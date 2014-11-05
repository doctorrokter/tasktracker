package by.tasktracker.core.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

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

}
