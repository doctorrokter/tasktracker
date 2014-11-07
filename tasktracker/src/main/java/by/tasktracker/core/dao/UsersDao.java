package by.tasktracker.core.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import by.tasktracker.core.models.Role;
import by.tasktracker.core.models.User;

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
	 * ����� ������������ �� id.
	 * @param id ���������� ������������� ������������
	 * @return ������ ���� User, ��������� �� id.
	 */
	public User findUserById(int id) {
		return (User) find(User.class, "WHERE id = ?", id);
	}
	
	/**
	 * ����� ������������ �� ������.
	 * @param login ���������� ����� ������������.
	 * @return ������ ���� User, ��������� �� ������.
	 */
	public User findUserByLogin(String login) {
		return (User) find(User.class, "WHERE login = ?", login);
	}
	
	/**
	 * ����� ������������ �� ������ � ������.
	 * @param login ���������� ����� ������������.
	 * @param password ������ ������������.
	 * @return ������ ���� User, ��������� �� ������
	 */
	public User findUserByCredentials(String login, String password) {
		return (User) find(User.class, "WHERE login = ? AND password = ?", login, password);
	}
	
	/**
	 * ������� ���� ������������� � �������
	 * @return ������ ���� �������������.
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return (List<User>) findAll(User.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findUsersByRole(Role role) {
		return (List<User>) findAll(User.class, ", users_roles ur WHERE ur.role_id = ? AND users.id = ur.user_id", role.getId());
	}
	
	public void assignRole(User user, Role role) {
		execute("INSERT INTO users_roles (user_id, role_id) VALUES (" + user.getId() + ", " + role.getId() + ")");
	}
	
	public int createUser(User user) {
		user.setCreatedAt(new Timestamp(new Date().getTime()));
		user.setUpdatedAt(new Timestamp(new Date().getTime()));
		return save(user);
	}
	
	public void deleteUser(User user) {
		delete(user);
	}
	
}
