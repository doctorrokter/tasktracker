package by.tasktracker.core.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import by.tasktracker.core.models.Comment;

public class CommentsDaoTest {

	private CommentsDao commentsDao;
	
	public CommentsDaoTest() {
		commentsDao = CommentsDao.getCommentsDao();
	}
	
	@Test
	public void getCommentUserTest() {
		Comment c = new Comment();
		c.setUserId(2);
		c.setUser(commentsDao.getUserByComment(c));
		assertTrue(c.getUser().getLogin().equals("test"));
	}

}
