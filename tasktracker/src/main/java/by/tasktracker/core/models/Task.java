package by.tasktracker.core.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import by.tasktracker.core.annotations.Column;
import by.tasktracker.core.annotations.Join;
import by.tasktracker.core.annotations.Table;
import by.tasktracker.core.sql.Sql;

@Table(name = "tasks")
public class Task extends AbstractModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7967787996928931546L;

	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "parent_id")
	private int parentId;
	
	@Column(name = "status_id")
	private int statusId;
	
	@Column(name = "category_id")
	private int categoryId;
	
	@Column(name = "deadline")
	private Timestamp deadline;
	
	@Column(name = "creator_id")
	private int creatorId;
	
	@Column(name = "assignee_id")
	private int assigneeId;
	
	@Column(name = "workflow_id")
	private int workflowId;
	
	@Column(name = "progress")
	private int progress;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@Join(type = Sql.JOIN.INNER, sourceColumn = "status_id", table = "statuses", alias = "s", targetColumn = "id")
	private Status status;
	
	@Join(type = Sql.JOIN.INNER, sourceColumn = "category_id", table = "categories", alias = "c", targetColumn = "id")
	private Category category;
	
	@Join(type = Sql.JOIN.LEFT, sourceColumn = "parent_id", table = "tasks", alias = "t1", targetColumn = "id")
	private Task parent;
	
	@Join(type = Sql.JOIN.INNER, sourceColumn = "creator_id", table = "users", alias = "u1", targetColumn = "id")
	private User creator;
	
	@Join(type = Sql.JOIN.INNER, sourceColumn = "assignee_id", table = "users", alias = "u2", targetColumn = "id")
	private User assignee;
	
	private List<Comment> comments;
	
	public Task() {
		
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Timestamp getDeadline() {
		return deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public int getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(int assigneeId) {
		this.assigneeId = assigneeId;
	}

	public int getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(int workflowId) {
		this.workflowId = workflowId;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Task getParent() {
		return parent;
	}

	public void setParent(Task parent) {
		this.parent = parent;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assignee == null) ? 0 : assignee.hashCode());
		result = prime * result + assigneeId;
		result = prime * result + categoryId;
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + creatorId;
		result = prime * result
				+ ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + parentId;
		result = prime * result + progress;
		result = prime * result + statusId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Task other = (Task) obj;
		if (assignee == null) {
			if (other.assignee != null)
				return false;
		} else if (!assignee.equals(other.assignee))
			return false;
		if (assigneeId != other.assigneeId)
			return false;
		if (categoryId != other.categoryId)
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (creatorId != other.creatorId)
			return false;
		if (deadline == null) {
			if (other.deadline != null)
				return false;
		} else if (!deadline.equals(other.deadline))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (parentId != other.parentId)
			return false;
		if (progress != other.progress)
			return false;
		if (statusId != other.statusId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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
