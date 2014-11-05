package by.tasktracker.web.controllers;

import by.tasktracker.core.dao.RolesDao;
import by.tasktracker.core.dao.UsersDao;
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
		param("usersList", usersDao.getAllUsers());
		forward("/WEB-INF/pages/layout/_default_layout.jsp");
	}
	
	public void info() {
		User user = usersDao.findUserById(getId());
		param("userInfo", true);
		param("userObj", user);
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
		user.setManagerId(Integer.parseInt(param("manager")));
		user.setId(usersDao.createUser(user));
		redirect("/users/info/" + user.getId());
	}
	
}
