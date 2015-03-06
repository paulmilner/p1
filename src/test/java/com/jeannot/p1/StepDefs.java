package com.jeannot.p1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import com.jeannot.p1.dto.Worker;
import com.jeannot.p1.dto.WorkerStatus;
import com.jeannot.p1.dto.WorkerType;
import com.jeannot.p1.exception.RestfulApplicationException;
import com.jeannot.p1.services.HistoryService;
import com.jeannot.p1.services.WorkerPersistenceService;
import com.jeannot.p1.services.WorkerPromotionService;
import com.jeannot.p1.services.impl.RestfulWorkerPersistenceService;
import com.jeannot.p1.services.impl.SimpleWorkerPromotionService;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {
	
	private Worker worker;
	private Throwable thrown;
	private WorkerPersistenceService workerPersistenceService;
	private WorkerPromotionService workerPromotionService;
	private long workerId;
	
	private HistoryService historyService;
	
	@Before
	public void setup() throws Exception {
        historyService = mock(HistoryService.class);
		workerPersistenceService = new RestfulWorkerPersistenceService(historyService);
		workerPromotionService = new SimpleWorkerPromotionService();
	}
	
	@When("^I create a worker called \"(.*?)\"$")
	public void i_create_a_worker_called(String name) throws Throwable {
		worker = new Worker(name,WorkerType.MINOR,WorkerStatus.DORMANT);
	}

	@Then("^the worker is called \"(.*?)\"$")
	public void the_worker_is_called(String name) throws Throwable {
		assertEquals("Name should match",name,worker.getName());
	}

	@Then("^the worker type is minor$")
	public void the_worker_type_is_minor() throws Throwable {
		assertEquals(WorkerType.MINOR,worker.getWorkerType());
	}

	@Then("^the worker's status is dormant$")
	public void the_worker_s_status_is_dormant() throws Throwable {
		assertEquals(WorkerStatus.DORMANT,worker.getWorkerStatus());
	}

	@When("^I try to create a worker with no name$")
	public void i_try_to_create_a_worker_with_no_name() throws Throwable {
		try {
			worker = new Worker(null,null,null);
		}
		catch (Exception e) {
			thrown = e;
		}
	}

	@Then("^I get an error$")
	public void i_get_an_error() throws Throwable {
		assertNotNull(thrown);
	}
	
	@When("^I store it in the database$")
	public void i_store_it() throws Throwable {
	    workerId = workerPersistenceService.create(worker);
	}

	@Then("^I can retrieve it$")
	public void i_can_retrieve_it() throws Throwable {
		Worker retrievedWorker = workerPersistenceService.retrieve(workerId);
		assertEquals(worker,retrievedWorker);
	}

	@When("^I promote and update it$")
	public void i_promote_and_update_it() throws Throwable {
		Worker promotedWorker = workerPromotionService.promoteWorker(worker);
		workerPersistenceService.update(workerId, promotedWorker);
	}

	@Then("^the updated worker can be retrieved$")
	public void the_updated_worker_can_be_retrieved() throws Throwable {
		Worker retrievedWorker = workerPersistenceService.retrieve(workerId);
		assertEquals(WorkerType.MAJOR,retrievedWorker.getWorkerType());
	}

	@When("^I delete it$")
	public void i_delete_it() throws Throwable {
		workerPersistenceService.delete(workerId);
	}

	@Then("^that worker can no longer be retrieved$")
	public void that_worker_can_no_longer_be_retrieved() throws Throwable {
		try {
			workerPersistenceService.retrieve(workerId);
			fail("Exception should have been thrown on retrieving a deleted Worker");
		} catch (RestfulApplicationException rae) {
			assertNotNull(rae);
		}
	}
}
