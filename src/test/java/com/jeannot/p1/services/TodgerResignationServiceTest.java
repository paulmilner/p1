package com.jeannot.p1.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jeannot.p1.dto.Todger;
import com.jeannot.p1.dto.TodgerStatus;
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
        Todger todger = new Todger("Alan",TodgerType.MINOR, TodgerStatus.THRUSTING);
        Todger resignedTodger = todgerResignationService.resign(todger);
        assertEquals(todger.getName(),resignedTodger.getName());
        assertEquals(todger.getTodgerType(),resignedTodger.getTodgerType());
        assertEquals(resignedTodger.getTodgerStatus(),TodgerStatus.RESIGNED);
    }
    
    @Test
    public void todgerFired() throws Exception {
        Todger todger = new Todger("Alan",TodgerType.MAJOR, TodgerStatus.THRUSTING);
        Todger firedTodger = todgerResignationService.fire(todger);
        assertEquals(todger.getName(),firedTodger.getName());
        assertEquals(todger.getTodgerType(),firedTodger.getTodgerType());
        assertEquals(firedTodger.getTodgerStatus(),TodgerStatus.FIRED);
    }
    

}
