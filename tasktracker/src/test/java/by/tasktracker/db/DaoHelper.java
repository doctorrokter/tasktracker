package by.tasktracker.db;

import java.sql.Timestamp;
import java.util.Date;

import by.tasktracker.core.models.Comment;
import by.tasktracker.core.models.Task;
import by.tasktracker.core.models.User;

public class DaoHelper {

	private DaoHelper() {
		
	}
	
	public static Task createTask() {
		Task task = new Task();
		task.setTitle("Test project");
		task.setDescription("Test description");
		task.setCategoryId(1);
		task.setCreatorId(1);
		task.setAssigneeId(1);
		task.setWorkflowId(1);
		task.setStatusId(1);
		task.setCreatedAt(new Timestamp(new Date().getTime()));
		task.setUpdatedAt(new Timestamp(new Date().getTime()));
		return task;
	}
	
	public static Comment createTaskComment() {
		Comment comment = new Comment();
		comment.setComment("test comment");
		comment.setUserId(1);
		comment.setCreatedAt(new Timestamp(new Date().getTime()));
		comment.setUpdatedAt(new Timestamp(new Date().getTime()));
		return comment;
	}
	
	public static User createUser() {
		User user = new User();
		user.setLogin("test_user");
		user.setPassword("test123");
		user.setFirstName("Mike");
		user.setLastName("Oldfield");
		user.setEmail("test@gmail.com");
		user.setPhoneNumber("+375259000000");
		return user;
	}
	
}
