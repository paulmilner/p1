package com.jeannot.p1.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.jeannot.p1.exception.WorkerException;
import com.jeannot.p1.services.WorkerPromotionService;
import com.jeannot.p1.services.impl.SimpleWorkerPromotionService;

public class WorkerTest {
    
    private WorkerPromotionService workerPromotionService;
    
    @Before
    public void setup() throws Exception {
        workerPromotionService = new SimpleWorkerPromotionService();
    }
    
    @Test
    public void successfulWorkerCreation() {
        String workerName = "Alan";
        Worker worker = new Worker(workerName,WorkerType.MINOR, WorkerStatus.ACTIVE);
        assertEquals(worker.getName(),workerName);
        assertTrue(worker.getWorkerType()==WorkerType.MINOR);
    }

    @Test
    public void successfulWorkerPromotion() {
        String workerName = "Alan";
        Worker worker1 = new Worker(workerName,WorkerType.MINOR, WorkerStatus.ACTIVE);
        Worker promotedWorker1 = workerPromotionService.promoteWorker(worker1);
        assertTrue(promotedWorker1.getWorkerType()==WorkerType.MAJOR);
        Worker promotedWorker2 = workerPromotionService.promoteWorker(promotedWorker1);
        assertTrue(promotedWorker2.getWorkerType()==WorkerType.SUPREME);
        try {
            Worker promotedWorker3 = workerPromotionService.promoteWorker(promotedWorker2);
            fail("An exception should have been thrown because the Worker cannot be promoted any further");
        }
        catch (WorkerException te) {
            assertNotNull(te);
        }
        
    }

}
