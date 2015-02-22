package com.jeannot.p1.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.jeannot.p1.dto.Worker;
import com.jeannot.p1.dto.WorkerStatus;
import com.jeannot.p1.dto.WorkerType;
import com.jeannot.p1.exception.WorkerPersistenceException;
import com.jeannot.p1.services.impl.RestfulWorkerPersistenceService;

public class RestfulWorkerPersistenceServiceTest {
    
    private WorkerPersistenceService workerPersistenceService;

    @Before
    public void setup() throws Exception {
        workerPersistenceService = new RestfulWorkerPersistenceService();
    }
    
    @Test
    public void workerCreation() throws Exception {
        Worker worker = new Worker("Alan",WorkerType.MINOR, WorkerStatus.ACTIVE);
        long id = workerPersistenceService.create(worker);
        Worker retrievedWorker = workerPersistenceService.retrieve(id);
        assertEquals(worker,retrievedWorker);
    }
    
    @Test
    public void workerUpdate() throws Exception {
        Worker worker = new Worker("Alan",WorkerType.MINOR, WorkerStatus.ACTIVE);
        long id = workerPersistenceService.create(worker);
        worker = new Worker("Brian",WorkerType.MAJOR, WorkerStatus.DORMANT);
        workerPersistenceService.update(id,worker);
        Worker retrievedWorker = workerPersistenceService.retrieve(id);
        assertEquals(worker,retrievedWorker);
    }
    
    @Test
    public void workerDeletion() throws Exception {
        Worker worker = new Worker("Alan",WorkerType.MINOR, WorkerStatus.ACTIVE);
        long id = workerPersistenceService.create(worker);
        workerPersistenceService.delete(id);
        try {
            workerPersistenceService.retrieve(id);
            fail("Exception should have been thrown as Worker has been deleted");
        } catch (WorkerPersistenceException tpe) {
            assertNotNull(tpe);
        }
    }
    
    @Test
    public void workerIds() throws Exception {
        Worker worker = new Worker("Alan",WorkerType.MINOR, WorkerStatus.ACTIVE);
        long id1 = workerPersistenceService.create(worker);
        long id2 = workerPersistenceService.create(worker);
        long id3 = workerPersistenceService.create(worker);
        try {
            workerPersistenceService.delete(2536562L); //should not exist
            fail("Exception should have been thrown as Worker does not exist");
        } catch (WorkerPersistenceException tpe) {
            assertNotNull(tpe);
        }
        workerPersistenceService.delete(id2);
        Set<Long> keys = workerPersistenceService.getKeys();
        assertTrue(keys.contains(id1));
        assertTrue(keys.contains(id3));
        assertFalse(keys.contains(id2));
        
        Set<Worker> values = workerPersistenceService.getValues();
        assertTrue(values.contains(worker));
    }
    
}
