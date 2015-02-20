package com.jeannot.p1.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.jeannot.p1.dto.Todger;
import com.jeannot.p1.dto.TodgerStatus;
import com.jeannot.p1.dto.TodgerType;
import com.jeannot.p1.exception.TodgerPersistenceException;
import com.jeannot.p1.services.impl.SimpleTodgerPersistenceService;

public class SimpleTodgerPersistenceServiceTest {
    
    private TodgerPersistenceService todgerPersistenceService;
    

    @Before
    public void setup() throws Exception {
        todgerPersistenceService = new SimpleTodgerPersistenceService();
    }
    
    @Test
    public void todgerCreation() throws Exception {
        Todger todger = new Todger("Alan",TodgerType.MINOR, TodgerStatus.THRUSTING);
        long id = todgerPersistenceService.create(todger);
        Todger retrievedTodger = todgerPersistenceService.retrieve(id);
        assertEquals(todger,retrievedTodger);
    }
    
    @Test
    public void todgerUpdate() throws Exception {
        Todger todger = new Todger("Alan",TodgerType.MINOR, TodgerStatus.THRUSTING);
        long id = todgerPersistenceService.create(todger);
        todger = new Todger("Brian",TodgerType.MAJOR, TodgerStatus.DORMANT);
        todgerPersistenceService.update(id,todger);
        Todger retrievedTodger = todgerPersistenceService.retrieve(id);
        assertEquals(todger,retrievedTodger);
    }
    
    @Test
    public void todgerDeletion() throws Exception {
        Todger todger = new Todger("Alan",TodgerType.MINOR, TodgerStatus.THRUSTING);
        long id = todgerPersistenceService.create(todger);
        todgerPersistenceService.delete(id);
        try {
            todgerPersistenceService.retrieve(id);
            fail("Exception should have been thrown as Todger has been deleted");
        } catch (TodgerPersistenceException tpe) {
            assertNotNull(tpe);
        }
    }
    
    @Test
    public void todgerIds() throws Exception {
        Todger todger = new Todger("Alan",TodgerType.MINOR, TodgerStatus.THRUSTING);
        long id1 = todgerPersistenceService.create(todger);
        assertEquals("1st ID should be 1",id1,1L);
        long id2 = todgerPersistenceService.create(todger);
        assertEquals("2nd ID should be 2",id2,2L);
        long id3 = todgerPersistenceService.create(todger);
        assertEquals("3rd ID should be 3",id3,3L);
        assertEquals("There should be 3 todgers in the map",3,todgerPersistenceService.getCount());
        try {
            todgerPersistenceService.delete(4L); //should not exist
            fail("Exception should have been thrown as Todger does not exist");
        } catch (TodgerPersistenceException tpe) {
            assertNotNull(tpe);
        }
        todgerPersistenceService.delete(2);
        assertEquals("There should be 2 todgers left in the map",2,todgerPersistenceService.getCount());
        Set<Long> keys = todgerPersistenceService.getKeys();
        assertTrue(keys.contains(1L));
        assertTrue(keys.contains(3L));
        assertFalse(keys.contains(2L));
        assertTrue(keys.size()==2);
        
        Set<Todger> values = todgerPersistenceService.getValues();
        assertTrue(values.contains(todger));
    }
    
}
