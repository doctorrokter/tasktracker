package by.tasktracker.core.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import by.tasktracker.core.models.AbstractModel;

public class Reflector {

	private static final Logger logger = Logger.getLogger(Reflector.class);
	
	private Reflector() {
		
	}
	
	public static String firstUpperCase(String str) {
		if(str == null || str.isEmpty()) return "";
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public static Field findField(String fieldName, Object obj) {
		Field [] fields = getAllFields(obj);
		for (Field f : fields) {
			if (f.getName().equals(fieldName)) {
				return f;
			}
		}
		return null;
	}
	
	public static Field[] getAllFields(Object obj) {
		return obj.getClass().getDeclaredFields();
	}
	
	public static Method findGetter(Field field, Object obj) {
		Class<?>[] params = null;
		Method method = null;
		try {
			method = obj.getClass().getMethod("get" + firstUpperCase(field.getName()), params);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
		return method;
	}
	
	public static Method findSetter(Field field, Object obj) {
		Class<?>[] params = {field.getType()};
		Method method = null;
		try {
			method = obj.getClass().getMethod("set" + firstUpperCase(field.getName()), params);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return method;
	}
	
	public static Object invokeGetter(Method method, Object target) {
		Object[] methodParams = null;
		Object result = null;
		try {
			result = method.invoke(target, methodParams);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
		return result;
	}
	
	public static void invokeSetter(Method method, Object target, Object value) {
		try {
			method.invoke(target, value);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public static Object createObjectByClass(Class<?> clazz) {
		Class<?>[] classParams = null;
		Object [] instanceParams = null;
		Object obj = null;
		try {
			Constructor<?> c = clazz.getConstructor(classParams);
			obj = (AbstractModel) c.newInstance(instanceParams);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
		return obj;
	}
}
