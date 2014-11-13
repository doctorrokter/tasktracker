package by.tasktracker.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация, помечающая класс как представление сущности (таблицы) в базе данных.
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Table {
	String name();
}
