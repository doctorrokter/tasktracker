package by.tasktracker.core.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import by.tasktracker.core.models.User;

public class UsersDao extends AbstractDao {

	private static final Logger logger = Logger.getLogger(UsersDao.class);
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
		logger.debug("Find user by id: " + id);
		return (User) find(User.class, "WHERE id = ?", id);
	}
	
	/**
	 * ����� ������������ �� ������.
	 * @param login ���������� ����� ������������.
	 * @return ������ ���� User, ��������� �� ������.
	 */
	public User findUserByLogin(String login) {
		logger.debug("Find user by login: " + login);
		return (User) find(User.class, "WHERE login = ?", login);
	}
	
	/**
	 * ����� ������������ �� ������ � ������.
	 * @param login ���������� ����� ������������.
	 * @param password ������ ������������.
	 * @return ������ ���� User, ��������� �� ������
	 */
	public User findUserByCredentials(String login, String password) {
		logger.debug("Find user by credentials: " + login);
		return (User) find(User.class, "WHERE login = ? AND password = ?", login, password);
	}
	
	/**
	 * ������� ���� ������������� � �������
	 * @return ������ ���� �������������.
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		logger.debug("Getting all users");
		return (List<User>) findAll(User.class);
	}
	
	public int createUser(User user) {
		user.setCreatedAt(new Timestamp(new Date().getTime()));
		user.setUpdatedAt(new Timestamp(new Date().getTime()));
		logger.debug("Creating user " + user);
		return save(user);
	}
	
	public void deleteUser(User user) {
		logger.debug("Deleting user by id = " + user.getId());
		delete(user);
	}
	
}
