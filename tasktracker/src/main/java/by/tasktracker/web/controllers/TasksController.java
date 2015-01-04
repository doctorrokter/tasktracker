package by.tasktracker.web.controllers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.tasktracker.config.MessagesBundle;
import by.tasktracker.core.dao.CategoriesDao;
import by.tasktracker.core.dao.CommentsDao;
import by.tasktracker.core.dao.TasksDao;
import by.tasktracker.core.dao.UsersDao;
import by.tasktracker.core.dao.WorkflowsDao;
import by.tasktracker.core.models.Category;
import by.tasktracker.core.models.Comment;
import by.tasktracker.core.models.Task;
import by.tasktracker.core.models.User;
import by.tasktracker.core.models.Workflow;
import by.tasktracker.core.util.Mailer;

/**
 * Контроллер для работы с задачами.
 */
public class TasksController extends MainController {
	
	private TasksDao tasksDao;
	private CategoriesDao categoriesDao;
	private UsersDao usersDao;
	private CommentsDao commentsDao;
	private WorkflowsDao workflowsDao;
		
	public TasksController() {
		tasksDao = TasksDao.getTasksDao();
		categoriesDao = CategoriesDao.getCategoriesDao();
		usersDao = UsersDao.getUsersDao();
		commentsDao = CommentsDao.getCommentsDao();
		workflowsDao = WorkflowsDao.getWorkflowsDao();
	}

	/**
	 * Метод для получения начальной страницы со списком всех задач.
	 */
	public void index() {
		param("tasksView", true);
		param("tasksTree", getTasksTree(tasksDao.getAllTasks()));
		forward(pagesLocation + "layout/" + getDefaultLayout());
	}
	
	/**
	 * Метод для получения информации по запрошенной пользователем задаче (по id).
	 */
	public void info() {
		Task task = tasksDao.findTaskById(getId());
		task.setComments(tasksDao.getTaskComments(task));
		param("tasksView", true);
		param("taskInfo", true);
		param("task", task);
		param("tasksTree", getTasksTree(tasksDao.getAllTasks()));
		forward(pagesLocation + "layout/" + getDefaultLayout());
	}
	
	/**
	 * Метод для получения страницы с формой создания задачи.
	 */
	public void create() {
		Category category = categoriesDao.findCategoryById(Integer.parseInt(param("categoryId")));
		category.getWorkflow().setStatuses(workflowsDao.getStatuses(category.getWorkflow()));
		if (category.getId() == 1) {
			Task parent = new Task();
			parent.setId(0);
			param("category", category);
			param("taskCreate", true);
			param("task", parent);
			param("usersList", usersDao.getAllUsers());
			forward(pagesLocation + "layout/" + getDefaultLayout());
		} else if (category.getId() == 2 || category.getId() == 3) {
			Task parent = tasksDao.findTaskById(Integer.parseInt(param("parentId")));
			if (parent.getCategory().getId() != 1) {
				param("tasksView", true);
				param("taskInfo", true);
				param("tasksTree", getTasksTree(tasksDao.getAllTasks()));
				param("task", parent);
				param("error", ((MessagesBundle) session("messages")).message("error.create.task.wrong.parent"));
				forward(pagesLocation + "layout/" + getDefaultLayout());
			} else {
				param("task", parent);
				param("category", category);
				param("taskCreate", true);
				param("usersList", usersDao.getAllUsers());
				forward(pagesLocation + "layout/" + getDefaultLayout());
			}
		}
	}
	
	/**
	 * Метод, обрабатывающий форму, пришедшей на сервер, для создания задачи.
	 */
	public void doCreate() {
		Task task = new Task();
		User user = (User) session("logged_user");
		task.setTitle(param("title"));
		task.setDescription(param("description"));
		task.setAssigneeId(Integer.parseInt(param("assigneeId")));
		task.setCreatorId(user.getId());
		task.setParentId(Integer.parseInt(param("parentId")));
		task.setStatusId(Integer.parseInt(param("statusId")));
		task.setCategoryId(Integer.parseInt(param("categoryId")));
		task.setWorkflowId(Integer.parseInt(param("workflowId")));
		task.setDeadline(new Timestamp(getDateFromString(param("deadline")).getTime()));
		task.setId(tasksDao.createTask(task));
		
		new Thread(new Mailer(user.getEmail(), "Updated [" + task.getId() + "] - " + task.getTitle(), task.getDescription())).start();
		new Thread(new Mailer(usersDao.findUserById(task.getAssigneeId()).getEmail(), "Updated [" + task.getId() + "] - " + task.getTitle(), task.getDescription())).start();
		
		redirect("/tasks/info/" + task.getId());
	}
	
	/**
	 * Метод для получения страницы с формой редактирования существующей задачи.
	 */
	public void update() {
		Task task = tasksDao.findTaskById(getId());
		Workflow w = categoriesDao.getWorkflow(task.getCategory());
		w.setStatuses(workflowsDao.getStatuses(w));
		task.getCategory().setWorkflow(w);
		param("taskUpdate", true);
		param("task", task);
		param("usersList", usersDao.getAllUsers());
		forward(pagesLocation + "layout/" + getDefaultLayout());
	}
	
	/**
	 * Метод, обрабатывающий форму, пришедшей на сервер, для редактирования задачи.
	 */
	public void doUpdate() {
		User user = (User) session("logged_user");
		Task task = tasksDao.findTaskById(Integer.parseInt(param("id")));
		task.setTitle(param("title"));
		task.setDescription(param("description"));
		task.setStatusId(Integer.parseInt(param("statusId")));
		task.setDeadline(new Timestamp(getDateFromString(param("deadline")).getTime()));
		task.setAssigneeId(Integer.parseInt(param("assigneeId")));
		task.setProgress(Integer.parseInt(param("progress")));
		tasksDao.updateTask(task);
		
		Comment comment = new Comment();
		comment.setComment(param("comment"));
		comment.setTaskId(task.getId());
		comment.setUserId(user.getId());
		commentsDao.createComment(comment);
		
		new Thread(new Mailer(user.getEmail(), "Updated [" + task.getId() + "] - " + task.getTitle(), task.getDescription())).start();
		new Thread(new Mailer(usersDao.findUserById(task.getAssigneeId()).getEmail(), "Updated [" + task.getId() + "] - " + task.getTitle(), task.getDescription())).start();
		
		redirect("/tasks/info/" + task.getId());
	}
	
	/**
	 * Метод для удаления задачи (по id).
	 */
	public void delete() {
		Task task = new Task();
		task.setId(getId());
		tasksDao.deleteTask(task);
		param("tasksTree", getTasksTree(tasksDao.getAllTasks()));
		param("tasksView", true);
		forward(pagesLocation + "layout/" + getDefaultLayout());
	}
	
	private Map<Task, List<Task>> getTasksTree(List<Task> tasks) {
		Map<Task, List<Task>> tree = new HashMap<>();
		for(Task task : tasks) {
			if (task.getParentId() == 0) {
				List<Task> subTasks = new ArrayList<>();
				for (Task subTask : tasks) {
					if (subTask.getParentId() == task.getId()) {
						subTasks.add(subTask);
					}
				}
				tree.put(task, subTasks);
			}
		}
		return tree;
	}
	
	private Date getDateFromString(String dateStr) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
