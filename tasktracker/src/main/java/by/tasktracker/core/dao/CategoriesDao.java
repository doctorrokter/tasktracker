package by.tasktracker.core.dao;

import java.util.List;

import by.tasktracker.core.models.Category;
import by.tasktracker.core.models.Task;

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
	
	public Category findCategoryById(int id) {
		return (Category) find(Category.class, "WHERE id = ?", id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() {
		return (List<Category>) findAll(Category.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Task> getTasksByCategory(Category category) {
		return (List<Task>) findAll(Task.class, "WHERE category_id = ?", category.getId());
	}
	
	public int createCategory(Category category) {
		return save(category);
	}
	
	public void deleteCategory(Category category) {
		delete(category);
	}
	
}
