package com.jeannot.p1.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;

import com.jeannot.p1.dto.Worker;
import com.jeannot.p1.dto.WorkerStatus;
import com.jeannot.p1.dto.WorkerType;
import com.jeannot.p1.services.impl.SimpleWorkerPromotionService;
import com.jeannot.p1.services.impl.SimpleWorkerResignationService;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PromotionServiceStepDefs {
	
	private Worker worker;
	private Throwable thrown;
	
	private WorkerPromotionService promotionService = new SimpleWorkerPromotionService();
				
	@Given("^a minor worker$")
	public void a_minor_worker() throws Throwable {
		worker = new Worker("Alan",null,null);
	}

	@When("^I promote it$")
	public void i_promote_it() throws Throwable {
		worker = promotionService.promoteWorker(worker);
	}

	@Then("^the worker goes up by one rank$")
	public void the_worker_goes_up_by_one_rank() throws Throwable {
		assertEquals(WorkerType.MAJOR,worker.getWorkerType());
	}
}
