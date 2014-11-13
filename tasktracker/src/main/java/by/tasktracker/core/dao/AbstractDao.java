package by.tasktracker.core.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.tasktracker.core.annotations.Column;
import by.tasktracker.core.annotations.Join;
import by.tasktracker.core.annotations.Table;
import by.tasktracker.core.models.AbstractModel;
import by.tasktracker.core.util.Reflector;
import by.tasktracker.db.DBManager;

public class AbstractDao {

	private static final Logger logger = Logger.getLogger(AbstractDao.class);
	private static Connection connection = null;
	
	static {
		connection = DBManager.getConnection();
	}

	public static Connection getConnection() {
		return connection;
	}
	
	protected int save(AbstractModel model) {
		Table table = model.getClass().getAnnotation(Table.class);
		String query = "INSERT INTO " + table.name();
		String columns = " (";
		String values = " VALUES(";
		
		Field [] modelFields = model.getClass().getDeclaredFields();
		for (Field f : modelFields) {
			if (!f.getName().equals("id")) {
				if (f.isAnnotationPresent(Column.class)) {
					columns += f.getAnnotation(Column.class).name() + ",";
					Object data = Reflector.invokeGetter(Reflector.findGetter(f, model), model);
					values = setValue(values, data) + ",";
				}
			}
		}
		columns = columns.substring(0, columns.lastIndexOf(",")) + ")";
		values = values.substring(0, values.lastIndexOf(",")) + ")";
		query = query + columns + values;
		return execute(query);
	}
	
	protected void delete(AbstractModel model) {
		Table table = model.getClass().getAnnotation(Table.class);
		Field id = Reflector.findField("id", model);
		int idVal = (Integer) Reflector.invokeGetter(Reflector.findGetter(id, model), model);
		String query = "DELETE FROM " + table.name() + " WHERE id = " + idVal;
		execute(query);
	}
	
	protected void update(AbstractModel model) {
		Table table = model.getClass().getAnnotation(Table.class);
		Field id = Reflector.findField("id", model);
		int idVal = (Integer) Reflector.invokeGetter(Reflector.findGetter(id, model), model);
		String query = "UPDATE " + table.name() + " SET ";
		Field [] modelFields = model.getClass().getDeclaredFields();
		for (Field f : modelFields) {
			if (!f.getName().equals("id")) {
				if (f.isAnnotationPresent(Column.class)) {
					query += f.getAnnotation(Column.class).name() + " = ";
					Object data = Reflector.invokeGetter(Reflector.findGetter(f, model), model);
					query = setValue(query, data) + ",";
				}
			}
		}
		query = query.substring(0, query.lastIndexOf(",")) + " WHERE id = " + idVal;
		execute(query);
	}
	
	protected void deleteAll(Class<? extends AbstractModel> clazz) {
		Table table = clazz.getAnnotation(Table.class);
		String query = "DELETE FROM " + table.name();
		execute(query);
	}
	
	protected AbstractModel find(Class<? extends AbstractModel> clazz, String subQuery, Object... params) {
		subQuery = convertQuery(subQuery, params);
		String query = "SELECT * FROM " + prepareQuery(clazz);
		query += subQuery;
		return executeQuery(query, clazz);
	}
	
	protected List<? extends AbstractModel> findAll(Class<? extends AbstractModel> clazz) {
		String query = "SELECT * FROM " + prepareQuery(clazz);
		return fetch(clazz, query);
	}
	
	protected List<? extends AbstractModel> findAll(Class<? extends AbstractModel> clazz, String subQuery, Object... params) {
		if (params != null || params.length != 0) {
			subQuery = convertQuery(subQuery, params);
		}
		String query = "SELECT * FROM " + prepareQuery(clazz);
		query += subQuery;
		return fetch(clazz, query);
	}
	
	protected int execute(String query) {
		int id = 0;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			logger.debug(query);
			statement = connection.createStatement();
			statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) id = resultSet.getInt(1);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(statement, resultSet);
		}
		return id;
	}
	
	protected AbstractModel executeQuery(String query, Class<? extends AbstractModel> clazz) {
		Statement statement = null;
		ResultSet resultSet = null;
		AbstractModel model = null;
		try {
			logger.debug(query);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				model = getModelFromResultSet(resultSet, clazz);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(statement, resultSet);
		}
		return model;
	}
	
	private List<AbstractModel> fetch(Class<? extends AbstractModel> clazz, String query) {
		Statement statement = null;
		ResultSet resultSet = null;
		List<AbstractModel> models = new ArrayList<AbstractModel>();
		try {
			logger.debug(query);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				models.add(getModelFromResultSet(resultSet, clazz));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(statement, resultSet);
		}
		return models;
	}
	
	@SuppressWarnings("unchecked")
	private AbstractModel getModelFromResultSet(ResultSet resultSet, Class<?> clazz) throws SQLException {
		AbstractModel model = (AbstractModel) Reflector.createObjectByClass(clazz);
		Field[] modelFields = model.getClass().getDeclaredFields();
		for (Field field : modelFields) {
			
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				Reflector.invokeSetter(Reflector.findSetter(field, model), model, resultSet.getObject(column.name()));
			}
			
			if (field.isAnnotationPresent(Join.class)) {
				Join join = field.getAnnotation(Join.class);
				Class<AbstractModel> childClazz = (Class<AbstractModel>) field.getType();
				AbstractModel child = (AbstractModel) Reflector.createObjectByClass(childClazz);
				Field[] childFields = childClazz.getDeclaredFields();
				for (Field f : childFields) {
					if (f.isAnnotationPresent(Column.class)) {
						Column column = f.getAnnotation(Column.class);
						Object value = resultSet.getObject(join.alias() + "_" + column.name());
						if (value == null) {
							continue;
						} else {
							Reflector.invokeSetter(Reflector.findSetter(f, child), child, resultSet.getObject(join.alias() + "_" + column.name()));
						}
					}
				}
				Reflector.invokeSetter(Reflector.findSetter(field, model), model, child);
			}
		}
		return model;
	}
	
	private String prepareQuery(Class<? extends AbstractModel> clazz) {
		Table table = clazz.getAnnotation(Table.class);
		String query = table.name() + "\n ";
		
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (f.isAnnotationPresent(Join.class)) {
				Join join = f.getAnnotation(Join.class);
				query += join.type() + " JOIN\n ";
				Field[] subFields = f.getType().getDeclaredFields();
				query += "(SELECT ";
				int i = 0;
				while (i < subFields.length) {
					if (subFields[i].isAnnotationPresent(Column.class)) {
						Column column = subFields[i].getAnnotation(Column.class);
						query += join.alias()+ "." + column.name() + " AS " + join.alias() + "_" + column.name() + ",\n";
					}
					i++;
				}
				query = query.substring(0, query.length() - 2);
				query += " FROM " + join.table() + " " + join.alias()+ ") ";
				query += join.alias() + "_CUR ON " + join.alias() + "_CUR." + join.alias() + "_" + join.targetColumn() + " = " + table.name() + "." + join.sourceColumn() + " ";
			}
		}
		return query;
	}
	
	private String convertQuery(String subQuery, Object... params) {
		for (Object o : params) {
			if (o instanceof String) {
				subQuery = subQuery.replaceFirst("\\?", "'" + o + "'");
			} else if (o instanceof Timestamp) {
				subQuery = subQuery.replaceFirst("\\?", "'" + o + "'");
			} else {
				subQuery = subQuery.replaceFirst("\\?", "" + o);
			}
		}
		return subQuery;
	}
	
	private String setValue(String subQuery, Object value) {
		if (value instanceof String) {
			subQuery += "'" + value + "'"; 
		} else if (value instanceof Timestamp) {
			subQuery += "'" + value + "'";
		} else {
			subQuery += value;
		}
		return subQuery;
	}
	
	private void close(Statement statement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			statement.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}
}
