package by.tasktracker.core.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Test;

import by.tasktracker.core.models.Status;
import by.tasktracker.core.models.Workflow;

public class WorkflowsDaoTest {

	private WorkflowsDao workflowsDao;
	
	public WorkflowsDaoTest() {
		workflowsDao = WorkflowsDao.getWorkflowsDao();
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getStatusesTest() {
		Workflow w = new Workflow();
		w.setId(1);
		
		List<Status> statuses = workflowsDao.getStatuses(w);
		assertTrue(statuses.size() > 0);
	}

}
