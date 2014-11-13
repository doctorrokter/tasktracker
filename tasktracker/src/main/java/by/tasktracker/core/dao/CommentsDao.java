package by.tasktracker.core.dao;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import by.tasktracker.core.models.Comment;
import by.tasktracker.core.models.User;

/**
 * Класс для работы с комментариями.
 */
public class CommentsDao extends AbstractDao {

	private static final Logger logger = Logger.getLogger(TasksDao.class);
	private static CommentsDao commentsDao = null;
	
	private CommentsDao() {
		
	}
	
	public static CommentsDao getCommentsDao() {
		if (commentsDao == null) {
			commentsDao = new CommentsDao();
		}
		return commentsDao;
	}
	
	/**
	 * Получение пользователя, оставившего комментарий.
	 * @param comment объект Comment.
	 * @return объект User.
	 */
	public User getUserByComment(Comment comment) {
		return (User) find(User.class, "WHERE id = ?", comment.getUserId());
	}
	
	/**
	 * Создание комментария.
	 * @param comment объект Comment.
	 * @return созданный id комментария.
	 */
	public int createComment(Comment comment) {
		comment.setCreatedAt(new Timestamp(new Date().getTime()));
		comment.setUpdatedAt(new Timestamp(new Date().getTime()));
		logger.debug("Creating comment " + comment);
		return save(comment);
	}
	
	/**
	 * Удаление комментария.
	 * @param comment объект Comment.
	 */
	public void deleteComment(Comment comment) {
		logger.debug("Deleting comment " + comment);
		delete(comment);
	}
	
}
