package com.jeannot.p1.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;

import com.jeannot.p1.dto.Todger;
import com.jeannot.p1.dto.TodgerStatus;
import com.jeannot.p1.dto.TodgerType;
import com.jeannot.p1.services.impl.SimpleTodgerPromotionService;
import com.jeannot.p1.services.impl.SimpleTodgerResignationService;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PromotionServiceStepDefs {
	
	private Todger todger;
	private Throwable thrown;
	
	private TodgerPromotionService promotionService = new SimpleTodgerPromotionService();
				
	@Given("^a minor todger$")
	public void a_minor_todger() throws Throwable {
		todger = new Todger("Alan",null,null);
	}

	@When("^I promote it$")
	public void i_promote_it() throws Throwable {
		todger = promotionService.promoteTodger(todger);
	}

	@Then("^the todger goes up by one rank$")
	public void the_todger_goes_up_by_one_rank() throws Throwable {
		assertEquals(TodgerType.MAJOR,todger.getTodgerType());
	}
}
