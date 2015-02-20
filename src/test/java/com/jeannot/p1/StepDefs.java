package com.jeannot.p1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.jeannot.p1.dto.Todger;
import com.jeannot.p1.dto.TodgerStatus;
import com.jeannot.p1.dto.TodgerType;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {
	
	private Todger todger;
	private Throwable thrown;
	
	@When("^I create a todger called \"(.*?)\"$")
	public void i_create_a_todger_called(String name) throws Throwable {
		todger = new Todger(name,TodgerType.MINOR,TodgerStatus.DORMANT);
	}

	@Then("^the todger is called \"(.*?)\"$")
	public void the_todger_is_called(String name) throws Throwable {
		assertEquals("Name should match",name,todger.getName());
	}

	@Then("^the todger type is minor$")
	public void the_todger_type_is_minor() throws Throwable {
		assertEquals(TodgerType.MINOR,todger.getTodgerType());
	}

	@Then("^the todger's status is dormant$")
	public void the_todger_s_status_is_dormant() throws Throwable {
		assertEquals(TodgerStatus.DORMANT,todger.getTodgerStatus());
	}

	@When("^I try to create a todger with no name$")
	public void i_try_to_create_a_todger_with_no_name() throws Throwable {
		try {
			todger = new Todger(null,null,null);
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
