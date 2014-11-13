package by.tasktracker.core.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import by.tasktracker.core.models.Category;
import by.tasktracker.core.models.Task;
import by.tasktracker.db.DaoHelper;

public class CategoriesDaoTest extends AbstractDao{

	private CategoriesDao categoriesDao;
	
	public CategoriesDaoTest() {
		categoriesDao = CategoriesDao.getCategoriesDao();
	}
	
	@After
	public void tearDown() {
		deleteAll(Task.class);
	}
	
	@Test
	public void findCategoryByIdTest() {
		Category c = categoriesDao.findCategoryById(1);
		assertTrue(c != null);
		assertTrue(c.getName().equals("Project"));
	}
	
	@Test
	public void getAllCategoriesTest() {
		assertTrue(categoriesDao.getAllCategories().size() > 0);
	}
	
	@Test
	public void getTasksByCategoryTest() {
		Task t1 = DaoHelper.createTask();
		save(t1);
		
		Task t2 = DaoHelper.createTask();
		save(t2);
		
		Category c = new Category();
		c.setId(1);
		assertTrue(categoriesDao.getTasksByCategory(c).size() == 2);
	}

}
