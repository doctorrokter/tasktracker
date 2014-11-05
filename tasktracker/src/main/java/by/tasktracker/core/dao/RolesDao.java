package by.tasktracker.core.dao;

import java.util.List;

import by.tasktracker.core.models.Role;

public class RolesDao extends AbstractDao {

	private static RolesDao rolesDao;
	
	private RolesDao() {
		
	}
	
	public static RolesDao getRolesDao() {
		if (rolesDao == null) {
			rolesDao = new RolesDao();
		}
		return rolesDao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> getAllRoles() {
		return (List<Role>) findAll(Role.class);
	}
}
