package com.jeannot.p1.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jeannot.p1.dto.Todger;
import com.jeannot.p1.dto.TodgerType;
import com.jeannot.p1.services.impl.SimpleTodgerResignationService;

public class TodgerResignationServiceTest {
    
    private TodgerResignationService todgerResignationService;
    

    @Before
    public void setup() throws Exception {
        todgerResignationService = new SimpleTodgerResignationService();
    }
    

    @Test
    public void todgerResignation() throws Exception {
        Todger todger = new Todger("Alan",TodgerType.MINOR);
        Todger resignedTodger = todgerResignationService.resign(todger);
        assertEquals(todger.getName(),resignedTodger.getName());
        assertEquals(resignedTodger.getTodgerType(),TodgerType.RESIGNED);
    }
    
    @Test
    public void todgerFired() throws Exception {
        Todger todger = new Todger("Alan",TodgerType.MAJOR);
        Todger firedTodger = todgerResignationService.fire(todger);
        assertEquals(todger.getName(),firedTodger.getName());
        assertEquals(firedTodger.getTodgerType(),TodgerType.FIRED);
    }
    

}
