package by.tasktracker.db;

import java.sql.Timestamp;
import java.util.Date;

import by.tasktracker.core.models.Comment;
import by.tasktracker.core.models.Task;

public class DaoHelper {

	private DaoHelper() {
		
	}
	
	public static Task createTask() {
		Task task = new Task();
		task.setTitle("Test project");
		task.setDescription("Test description");
		task.setCategoryId(1);
		task.setCreatorId(2);
		task.setAssigneeId(2);
		task.setWorkflowId(1);
		task.setStatusId(1);
		task.setCreatedAt(new Timestamp(new Date().getTime()));
		task.setUpdatedAt(new Timestamp(new Date().getTime()));
		return task;
	}
	
	public static Comment createTaskComment() {
		Comment comment = new Comment();
		comment.setComment("test comment");
		comment.setUserId(2);
		comment.setCreatedAt(new Timestamp(new Date().getTime()));
		comment.setUpdatedAt(new Timestamp(new Date().getTime()));
		return comment;
	}
	
}
