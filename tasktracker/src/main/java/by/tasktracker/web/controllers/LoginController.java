package by.tasktracker.web.controllers;

import by.tasktracker.core.dao.RolesDao;
import by.tasktracker.core.dao.UsersDao;
import by.tasktracker.core.models.User;

/**
 * Контроллер для авторизации пользователей.
 */
public class LoginController extends MainController {

	private UsersDao usersDao;
	private RolesDao rolesDao;
	
	public LoginController() {
		usersDao = UsersDao.getUsersDao();
		rolesDao = RolesDao.getRolesDao();
	}
	
	/**
	 * Провека пользователя на присутствие в системе. Если такой пользователь есть,
	 * получаем объект типа User и помещаем его в сессию. Если нет, редиректим на 
	 * начальную страницу.
	 */
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
	
	/**
	 * Выход пользователя из системы (убиваем объект в сессии).
	 */
	public void logout() {
		session("logged_user", null);
		redirect("/");
	}
	
}
