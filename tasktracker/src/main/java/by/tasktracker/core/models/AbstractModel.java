package by.tasktracker.core.models;

import java.lang.reflect.Field;

import by.tasktracker.core.util.Reflector;

public abstract class AbstractModel {

	@Override
	public String toString() {
		Field[] fields = Reflector.getAllFields(this);
		String toString = "{";
		for (Field f : fields) {
			if (!f.getName().equals("serialVersionUID")) {
				toString += "\"" + f.getName() + "\"" + ":" + "\"" + Reflector.invokeGetter(Reflector.findGetter(f, this), this) + "\", ";
			}
		}
		toString += "}";
		return toString;
	}
	
}
