package by.tasktracker.core.util;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import by.tasktracker.core.models.Task;

public class ReflectorTest {

	private Task task;
	
	@Before
	public void setUp() {
		task = new Task();
		task.setId(1);
		task.setTitle("test title");
	}
	
	@Test
	public void findFieldTest() {
		assertTrue(Reflector.findField("id", task) != null);
	}
	
	@Test
	public void findGetterTest() throws NoSuchFieldException, SecurityException {
		Method method = Reflector.findGetter(Reflector.findField("id", task), task);
		assertTrue(method != null);
	}
	
	@Test
	public void findSetterTest() {
		Method method = Reflector.findSetter(Reflector.findField("title", task), task);
		assertTrue(method != null);
	}
	
	@Test
	public void invokeGetterTest() {
		Method method = Reflector.findGetter(Reflector.findField("id", task), task);
		assertTrue((Integer)Reflector.invokeGetter(method, task) == 1);
	}
	
	@Test
	public void invokeSetterTest() {
		Method method = Reflector.findSetter(Reflector.findField("title", task), task);
		assertTrue(task.getTitle().equals("test title"));
		
		Reflector.invokeSetter(method, task, "new title");
		assertTrue(task.getTitle().equals("new title"));
	}
	
	@Test
	public void createObjectByClassTest() {
		Task t = (Task) Reflector.createObjectByClass(Task.class);
		assertTrue(t != null);
	}

}
