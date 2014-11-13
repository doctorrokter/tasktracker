package by.tasktracker.core.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import by.tasktracker.core.models.Comment;
import by.tasktracker.core.models.Task;
import by.tasktracker.db.DaoHelper;

public class TasksDaoTest extends AbstractDao {

	private TasksDao tasksDao;
	
	public TasksDaoTest() {
		tasksDao = TasksDao.getTasksDao();
		save(DaoHelper.createTask());
		save(DaoHelper.createTask());
		save(DaoHelper.createTask());
	}
	
	@After
	public void tearDown() throws Exception {
		deleteAll(Task.class);
	}
	
	@Test
	public void findTaskByIdTest() {
		Task task = DaoHelper.createTask();
		task.setId(save(task));
		
		Task newTask = tasksDao.findTaskById(task.getId());
		assertTrue(task.getTitle().equals(newTask.getTitle()));
	}
	
	@Test
	public void getTaskStatusByStatusIdTest() {
		Task task = DaoHelper.createTask();
		task.setId(save(task));
		
		task = tasksDao.findTaskById(task.getId());
		assertTrue(task.getStatus() != null);
		assertTrue(task.getStatus().getName().equals("New"));
	}
	
	@Test
	public void getTaskCategoryByCategoryIdTest() {
		Task task = DaoHelper.createTask();
		task.setId(save(task));
		
		task = tasksDao.findTaskById(task.getId());
		assertTrue(task.getCategory() != null);
		assertTrue(task.getCategory().getName().equals("Project"));
	}
	
	@Test
	public void getParentTaskTest() {
		Task parent = DaoHelper.createTask();
		parent.setTitle("Parent");
		parent.setId(save(parent));
		
		Task child = DaoHelper.createTask();
		child.setParentId(parent.getId());
		child.setId(save(child));
		
		child = tasksDao.findTaskById(child.getId());
		assertTrue(child.getParent() != null);
		assertTrue(child.getParent().getTitle().equals("Parent"));
	}
	
	@Test
	public void getTaskCreatorTest() {
		Task task = DaoHelper.createTask();
		task.setId(save(task));
		
		task = tasksDao.findTaskById(task.getId());
		assertTrue(task.getCreator() != null);
		assertTrue(task.getCreator().getLogin().equals("test"));
	}
	
	@Test
	public void getTaskAssigneeTest() {
		Task task = DaoHelper.createTask();
		task.setId(save(task));
		
		task = tasksDao.findTaskById(task.getId());
		assertTrue(task.getAssignee() != null);
		assertTrue(task.getAssignee().getLogin().equals("test"));
	}
	
	@Test
	public void getTaskCommentsTest() {
		Task task1 = DaoHelper.createTask();
		task1.setId(save(task1));
		
		Task task2 = DaoHelper.createTask();
		task2.setId(save(task2));
		
		Comment c1 = DaoHelper.createTaskComment();
		c1.setTaskId(task1.getId());
		
		Comment c2 = DaoHelper.createTaskComment();
		c2.setTaskId(task1.getId());
		
		Comment c3 = DaoHelper.createTaskComment();
		c3.setTaskId(task2.getId());
		
		save(c1);
		save(c2);
		save(c3);
		
		task1.setComments(tasksDao.getTaskComments(task1));
		assertTrue(task1.getComments().size() == 2);
	}
}
