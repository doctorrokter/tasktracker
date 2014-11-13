package by.tasktracker.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import by.tasktracker.core.sql.Sql.JOIN;

/**
 * Аннотация для выполнения JOIN-ов при динамической генерации
 * SQL-запросов.
 */
@Documented
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Join {
	/**
	 * Тип join-a. Типы перечислены в классе by.tasktracker.core.Sql
	 */
	JOIN type();
	
	/**
	 * Имя поля исходной таблицы, по значение которого будем искать в присоединяемой таблице.
	 */
	String sourceColumn();
	
	/**
	 * Имя присоединяемой таблицы
	 */
	String table();
	
	/**
	 * Алиас присоединяемой таблицы (на случай, если имя таблицы будет использовано в нескольких join-ах).
	 */
	String alias();
	
	/**
	 * Целевой столбец присоединяемой таблицы, по которому будем искать данные.
	 */
	String targetColumn();
}
