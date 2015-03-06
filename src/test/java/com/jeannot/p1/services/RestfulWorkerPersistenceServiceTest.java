package com.jeannot.p1.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.jeannot.p1.dto.Worker;
import com.jeannot.p1.dto.WorkerStatus;
import com.jeannot.p1.dto.WorkerType;
import com.jeannot.p1.exception.RestfulApplicationException;
import com.jeannot.p1.services.impl.RestfulWorkerPersistenceService;

public class RestfulWorkerPersistenceServiceTest {
    
    private WorkerPersistenceService workerPersistenceService;
    private HistoryService historyService;

    @Before
    public void setup() throws Exception {
        historyService = mock(HistoryService.class);
        workerPersistenceService = new RestfulWorkerPersistenceService(historyService);
    }
    
    @Test
    public void worker_created() throws Exception {
        Worker worker = new Worker("Alan",WorkerType.MINOR, WorkerStatus.ACTIVE);
        long id = workerPersistenceService.create(worker);
        Worker retrievedWorker = workerPersistenceService.retrieve(id);
        assertEquals(worker,retrievedWorker);
    }
    
    @Test
    public void worker_updated() throws Exception {
        Worker worker = new Worker("Alan",WorkerType.MINOR, WorkerStatus.ACTIVE);
        long id = workerPersistenceService.create(worker);
        worker = new Worker("Brian",WorkerType.MAJOR, WorkerStatus.DORMANT);
        workerPersistenceService.update(id,worker);
        Worker retrievedWorker = workerPersistenceService.retrieve(id);
        assertEquals(worker,retrievedWorker);
    }
    
    @Test
    public void worker_deleted() throws Exception {
        Worker worker = new Worker("Alan",WorkerType.MINOR, WorkerStatus.ACTIVE);
        long id = workerPersistenceService.create(worker);
        workerPersistenceService.delete(id);
        try {
            workerPersistenceService.retrieve(id);
            fail("Exception should have been thrown as Worker has been deleted");
        } catch (RestfulApplicationException rae) {
            assertNotNull(rae);
        }
    }
    
    @Test
    public void worker_id_allocation() throws Exception {
        Worker worker = new Worker("Alan",WorkerType.MINOR, WorkerStatus.ACTIVE);
        long id1 = workerPersistenceService.create(worker);
        long id2 = workerPersistenceService.create(worker);
        long id3 = workerPersistenceService.create(worker);
        try {
            workerPersistenceService.delete(2536562L); //should not exist
            fail("Exception should have been thrown as Worker does not exist");
        } catch (RestfulApplicationException rae) {
            assertNotNull(rae);
        }
        workerPersistenceService.delete(id2);
        Set<Long> keys = workerPersistenceService.getKeys();
        assertTrue(keys.contains(id1));
        assertTrue(keys.contains(id3));
        assertFalse(keys.contains(id2));
        
        Set<Worker> values = workerPersistenceService.getValues();
        assertTrue(values.contains(worker));
    }
    
    @Test
    public void worker_history_is_logged() throws Exception {
        Worker worker = new Worker("Alan",WorkerType.MINOR, WorkerStatus.ACTIVE);
        long id = workerPersistenceService.create(worker);
        workerPersistenceService.delete(id);
        verify(historyService,times(1)).addToHistory(any(Worker.class), anyString());
        verify(historyService,times(1)).addToHistory(anyString());
    }
    
}
