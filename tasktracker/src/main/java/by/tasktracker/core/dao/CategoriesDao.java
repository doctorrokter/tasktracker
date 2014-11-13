package by.tasktracker.core.dao;

import java.util.List;

import by.tasktracker.core.models.Category;
import by.tasktracker.core.models.Task;
import by.tasktracker.core.models.Workflow;

/**
 * Класс для работы с категориями (типами задач).
 */
public class CategoriesDao extends AbstractDao {

	private static CategoriesDao categoriesDao;
	
	private CategoriesDao() {
		
	}
	
	public static CategoriesDao getCategoriesDao() {
		if (categoriesDao == null) {
			categoriesDao = new CategoriesDao();
		}
		return categoriesDao;
	}
	
	/**
	 * Поиск категории по id.
	 * @param id идентификатор категории в базе данных.
	 * @return объект Category.
	 */
	public Category findCategoryById(int id) {
		return (Category) find(Category.class, "WHERE id = ?", id);
	}
	
	/**
	 * Получение процесса, привязанного к категории.
	 * @param category объект Category.
	 * @return объект Workflow.
	 */
	public Workflow getWorkflow(Category category) {
		return (Workflow) find(Workflow.class, "WHERE id = ?", category.getWorkflowId());
	}
	
	/**
	 * Получение всех категорий.
	 * @return список категорий List<Category>, имеющихся в системе.
	 */
	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() {
		return (List<Category>) findAll(Category.class);
	}
	
	/**
	 * Получение всех задач определенной категории.
	 * @param category объект Category.
	 * @return список задач List<Task>.
	 */
	@SuppressWarnings("unchecked")
	public List<Task> getTasksByCategory(Category category) {
		return (List<Task>) findAll(Task.class, "WHERE category_id = ?", category.getId());
	}
	
	/**
	 * Создание новой категории.
	 * @param category объект Category.
	 * @return созданный id категории.
	 */
	public int createCategory(Category category) {
		return save(category);
	}
	
	/**
	 * Удаление категории.
	 * @param category объект Category.
	 */
	public void deleteCategory(Category category) {
		delete(category);
	}
	
}
