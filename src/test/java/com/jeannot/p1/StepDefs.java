package com.jeannot.p1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.jeannot.p1.dto.Worker;
import com.jeannot.p1.dto.WorkerStatus;
import com.jeannot.p1.dto.WorkerType;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {
	
	private Worker worker;
	private Throwable thrown;
	
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
}
