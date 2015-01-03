package by.tasktracker.core.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import by.tasktracker.core.models.Role;
import by.tasktracker.core.models.User;

/**
 * Класс для работы с пользователями.
 */
public class UsersDao extends AbstractDao {

	private static UsersDao usersDao = null;
	
	private UsersDao() {
		
	}
	
	public static UsersDao getUsersDao() {
		if (usersDao == null) {
			usersDao = new UsersDao();
		}
		return usersDao;
	}
	
	/**
	 * Поиск пользователя по id. 
	 * @param id идентификатор пользователя в базе данных.
	 * @return объект User.
	 */
	public User findUserById(int id) {
		return (User) find(User.class, "WHERE id = ?", id);
	}
	
	/**
	 * Поиск пользователя по логину.
	 * @param login логин пользователя.
	 * @return объект User.
	 */
	public User findUserByLogin(String login) {
		return (User) find(User.class, "WHERE login = ?", login);
	}
	
	/**
	 * Поиск пользователя по логину и паролю.
	 * @param login логин пользователя.
	 * @param password пароль пользователя.
	 * @return объект User.
	 */
	public User findUserByCredentials(String login, String password) {
		return (User) find(User.class, "WHERE login = ? AND password = ?", login, password);
	}
	
	/**
	 * Получение всех пользователей.
	 * @return список пользователей List<User>.
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return (List<User>) findAll(User.class);
	}
	
	/**
	 * Получение пользователей с определенной ролью.
	 * @param role объект Role.
	 * @return список пользователей List<User>.
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUsersByRole(Role role) {
		return (List<User>) findAll(User.class, ", users_roles ur WHERE ur.role_id = ? AND users.id = ur.user_id", role.getId());
	}
	
	/**
	 * Назначение роли пользователю.
	 * @param user объект User.
	 * @param role объект Role.
	 */
	public void assignRole(User user, Role role) {
		execute("INSERT INTO users_roles (user_id, role_id) VALUES (" + user.getId() + ", " + role.getId() + ")");
	}
	
	/**
	 * Снять роли с пользователя.
	 * @param user объект User.
	 */
	public void removeRoles(User user) {
		execute("DELETE FROM users_roles WHERE user_id = " + user.getId());
	}
	
	/**
	 * Создание пользователя.
	 * @param user объект User.
	 * @return созданный id пользователя.
	 */
	public int createUser(User user) {
		user.setCreatedAt(new Timestamp(new Date().getTime()));
		user.setUpdatedAt(new Timestamp(new Date().getTime()));
		return save(user);
	}
	
	/**
	 * Обновление пользователя.
	 * @param user объект User.
	 */
	public void updateUser(User user) {
		user.setUpdatedAt(new Timestamp(new Date().getTime()));
		update(user);
	}
	
	/**
	 * Удаление пользователя.
	 * @param user объект User.
	 */
	public void deleteUser(User user) {
		delete(user);
	}
	
}
