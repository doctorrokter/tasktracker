package by.tasktracker.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.tasktracker.core.dao.RolesDao;
import by.tasktracker.core.dao.UsersDao;
import by.tasktracker.core.models.Role;
import by.tasktracker.core.models.User;

public class UsersController extends MainController {

	private UsersDao usersDao;
	private RolesDao rolesDao;
		
	public UsersController() {
		usersDao = UsersDao.getUsersDao();
		rolesDao = RolesDao.getRolesDao();
	}
	
	public void index() {
		param("usersView", true);
		param("usersTree", getUsersTree());
		forward("/WEB-INF/pages/layout/_default_layout.jsp");
	}
	
	public void info() {
		User user = usersDao.findUserById(getId());
		param("userInfo", true);
		param("user", user);
		param("usersTree", getUsersTree());
		param("usersView", true);
		forward("/WEB-INF/pages/layout/_default_layout.jsp");
	}
	
	public void create() {
		param("userCreate", true);
		param("rolesList", rolesDao.getAllRoles());
		param("usersList", usersDao.getAllUsers());
		forward("/WEB-INF/pages/layout/_default_layout.jsp");
	}
	
	public void doCreate() {
		User user = new User();
		user.setLogin(param("login"));
		user.setPassword(param("password"));
		user.setFirstName(param("firstName"));
		user.setLastName(param("lastName"));
		user.setEmail(param("email"));
		user.setPhoneNumber(param("phoneNumber"));
		String[] roles = paramArray("role");
		user.setId(usersDao.createUser(user));
		
		for (String roleIdStr : roles) {
			Role role = new Role();
			role.setId(Integer.parseInt(roleIdStr));
			usersDao.assignRole(user, role);
		}
		
		redirect("/users/info/" + user.getId());
	}
	
	public void update() {
		User user = usersDao.findUserById(getId());
		user.setRoles(rolesDao.getUserRoles(user));
		param("userUpdate", true);
		param("rolesList", rolesDao.getAllRoles());
		param("user", user);
		forward("/WEB-INF/pages/layout/_default_layout.jsp");
	}
	
	public void doUpdate() {
		User user = usersDao.findUserById(Integer.parseInt(param("id")));
		user.setLogin(param("login"));
		user.setPassword(param("password"));
		user.setFirstName(param("firstName"));
		user.setLastName(param("lastName"));
		user.setEmail(param("email"));
		user.setPhoneNumber(param("phoneNumber"));
		String[] roles = paramArray("role");
		usersDao.removeRoles(user);
		for (String roleIdStr : roles) {
			Role role = new Role();
			role.setId(Integer.parseInt(roleIdStr));
			usersDao.assignRole(user, role);
		}
		usersDao.updateUser(user);
		redirect("/users/info/" + user.getId());
	}
	
	public void delete() {
		User user = new User();
		user.setId(getId());
		usersDao.deleteUser(user);
		param("usersTree", getUsersTree());
		param("usersView", true);
		redirect("/users");
	}
	
	private Map<Role, List<User>> getUsersTree() {
		Map<Role, List<User>> usersTree = new HashMap<>();
		List<Role> roles = rolesDao.getAllRoles();
		for (Role r : roles) {
			usersTree.put(r, usersDao.findUsersByRole(r));
		}
		return usersTree;
	}
}
