package by.tasktracker.core.dao;

import java.util.List;

import by.tasktracker.core.models.Role;
import by.tasktracker.core.models.User;

/**
 * Класс для работы с ролями пользователей.
 */
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
	
	/**
	 * Получение всех ролей.
	 * @return список ролей List<Role>.
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getAllRoles() {
		return (List<Role>) findAll(Role.class, "ORDER BY id");
	}
	
	/**
	 * Получение ролей, назначенных пользователю.
	 * @param user объект User.
	 * @return список ролей, назначенных пользователю.
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getUserRoles(User user) {
		return (List<Role>) findAll(Role.class, ", users_roles ur WHERE ur.user_id = ? AND roles.id = ur.role_id", user.getId());
	}
}
