package by.tasktracker.core.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import by.tasktracker.core.annotations.Column;
import by.tasktracker.core.annotations.Join;
import by.tasktracker.core.annotations.Table;
import by.tasktracker.core.sql.Sql;

@Table(name = "categories")
public class Category extends AbstractModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3816110472352770481L;

	@Column(name = "id") 
	private int id;
	
	@Column(name = "name") 
	private String name;
	
	@Column(name = "created_at") 
	private Timestamp createdAt;
	
	@Column(name = "updated_at") 
	private Timestamp updatedAt;
	
	@Column(name = "workflow_id")
	private int workflowId;
	
	@Join(type = Sql.JOIN.INNER, sourceColumn = "workflow_id", table = "workflows", alias = "w", targetColumn = "id")
	private Workflow workflow;
	
	private List<Task> tasks;
	
	public Category() {
		
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public int getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(int workflowId) {
		this.workflowId = workflowId;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
		result = prime * result
				+ ((updatedAt == null) ? 0 : updatedAt.hashCode());
		result = prime * result + workflowId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tasks == null) {
			if (other.tasks != null)
				return false;
		} else if (!tasks.equals(other.tasks))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		if (workflowId != other.workflowId)
			return false;
		return true;
	}
	
}
