package com.jeannot.p1;

import static org.junit.Assert.assertTrue;

import com.jeannot.p1.dto.Todger;
import com.jeannot.p1.dto.TodgerStatus;
import com.jeannot.p1.dto.TodgerType;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {
	
	private Todger todger;
	
	@When("^I create a todger called \"(.*?)\"$")
	public void i_create_a_todger_called(String name) throws Throwable {
		todger = new Todger(name,TodgerType.MINOR,TodgerStatus.DORMANT);
	}

	@Then("^the todger is called \"(.*?)\"$")
	public void the_todger_is_called(String name) throws Throwable {
		assertTrue("Name should match",todger.getName().equals(name));
	}

}
