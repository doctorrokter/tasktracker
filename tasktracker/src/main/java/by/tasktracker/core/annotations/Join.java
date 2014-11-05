package by.tasktracker.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import by.tasktracker.core.sql.Sql.JOIN;

@Documented
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Join {
	JOIN type();
	String sourceColumn();
	String table();
	String alias();
	String targetColumn();
}
