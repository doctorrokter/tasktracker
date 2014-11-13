package by.tasktracker.core.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import by.tasktracker.core.models.Comment;
import by.tasktracker.core.models.Task;

/**
 * Класс для работы с задачами.
 */
public class TasksDao extends AbstractDao {

	private static final Logger logger = Logger.getLogger(TasksDao.class);
	private static TasksDao tasksDao = null;
	
	private TasksDao() {
		
	}
	
	public static TasksDao getTasksDao() {
		if (tasksDao == null) {
			tasksDao = new TasksDao();
		}
		return tasksDao;
	}
	
	/**
	 * Поиск задачи по id.
	 * @param id идентификатор задачи в базе данных.
	 * @return объект Task.
	 */
	public Task findTaskById(int id) {
		logger.debug("Find task by id: " + id);
		return (Task) find(Task.class, "WHERE tasks.id = ?", id);
	}
	
	/**
	 * Получение всех задач.
	 * @return список задач List<Task>.
	 */
	@SuppressWarnings("unchecked")
	public List<Task> getAllTasks() {
		logger.debug("Getting all tasks");
		return (List<Task>) findAll(Task.class, "ORDER BY parent_id");
	}
			
	/**
	 * Получение всех комментариев, привязанных к задаче.
	 * @param task объект Task.
	 * @return список комментариев List<Comment>.
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getTaskComments(Task task) {
		return (List<Comment>) findAll(Comment.class, "WHERE task_id = ?", task.getId());
	}
	
	/**
	 * Создание задачи.
	 * @param task объект Task.
	 * @return созданный id задачи.
	 */
	public int createTask(Task task) {
		task.setCreatedAt(new Timestamp(new Date().getTime()));
		task.setUpdatedAt(new Timestamp(new Date().getTime()));
		logger.debug("Creating task " + task);
		return save(task);
	}
	
	/**
	 * Удаление задачи.
	 * @param task объект Task.
	 */
	public void deleteTask(Task task) {
		logger.debug("Deleting task: id = " + task.getId());
		delete(task);
	}
	
	/**
	 * Обновление задачи.
	 * @param task объект Task.
	 */
	public void updateTask(Task task) {
		task.setUpdatedAt(new Timestamp(new Date().getTime()));
		logger.debug("Updating task: id = " + task.getId());
		update(task);
	}
}
