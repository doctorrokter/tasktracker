package by.tasktracker.core.dao;

import java.util.List;

import by.tasktracker.core.models.Status;
import by.tasktracker.core.models.Workflow;

public class WorkflowsDao extends AbstractDao {

	private static WorkflowsDao workflowsDao;
	
	private WorkflowsDao() {
		
	}
	
	public static WorkflowsDao getWorkflowsDao() {
		if (workflowsDao == null) {
			workflowsDao = new WorkflowsDao();
		}
		return workflowsDao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Status> getStatuses(Workflow workflow) {
		return (List<Status>) findAll(Status.class, ", workflows_statuses ws WHERE ws.workflow_id = ? AND statuses.id = ws.status_id", workflow.getId());
	}
	
}
