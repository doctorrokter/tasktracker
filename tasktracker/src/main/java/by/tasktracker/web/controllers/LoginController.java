package by.tasktracker.web.controllers;

import by.tasktracker.core.dao.RolesDao;
import by.tasktracker.core.dao.UsersDao;
import by.tasktracker.core.models.User;

public class LoginController extends MainController {

	private UsersDao usersDao;
	private RolesDao rolesDao;
	
	public LoginController() {
		usersDao = UsersDao.getUsersDao();
		rolesDao = RolesDao.getRolesDao();
	}
	
	public void auth() {
		User user = usersDao.findUserByCredentials(param("login"), param("password"));
		if (user == null) {
			param("bad_auth", true);
			forward("/index.jsp");
		} else {
			user.setRoles(rolesDao.getUserRoles(user));
			session("logged_user", user);
			redirect("/");
		}
	}
	
	public void logout() {
		session("logged_user", null);
		redirect("/");
	}
	
}
