package com.jeannot.p1.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.jeannot.p1.exception.TodgerException;
import com.jeannot.p1.services.TodgerPromotionService;
import com.jeannot.p1.services.impl.SimpleTodgerPromotionService;

public class TodgerTest {
    
    private TodgerPromotionService todgerPromotionService;
    
    @Before
    public void setup() throws Exception {
        todgerPromotionService = new SimpleTodgerPromotionService();
    }
    
    @Test
    public void successfulTodgerCreation() {
        String todgerName = "Alan";
        Todger todger = new Todger(todgerName,TodgerType.MINOR, TodgerStatus.THRUSTING);
        assertEquals(todger.getName(),todgerName);
        assertTrue(todger.getTodgerType()==TodgerType.MINOR);
    }

    @Test
    public void successfulTodgerPromotion() {
        String todgerName = "Alan";
        Todger todger1 = new Todger(todgerName,TodgerType.MINOR, TodgerStatus.THRUSTING);
        Todger promotedTodger1 = todgerPromotionService.promoteTodger(todger1);
        assertTrue(promotedTodger1.getTodgerType()==TodgerType.MAJOR);
        Todger promotedTodger2 = todgerPromotionService.promoteTodger(promotedTodger1);
        assertTrue(promotedTodger2.getTodgerType()==TodgerType.SUPREME);
        try {
            Todger promotedTodger3 = todgerPromotionService.promoteTodger(promotedTodger2);
            fail("An exception should have been thrown because the Todger cannot be promoted any further");
        }
        catch (TodgerException te) {
            assertNotNull(te);
        }
        
    }

}
