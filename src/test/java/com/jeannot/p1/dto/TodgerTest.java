package com.jeannot.p1.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TodgerTest {
    
    @Test
    public void successfulTodgerPromotion() {
        String todgerName = "Alan";
        Todger todger = new Todger(todgerName,TodgerType.MINOR);
        assertEquals(todger.getName(),todgerName);
        assertTrue(todger.getTodgerType()==TodgerType.MINOR);
    }

}
