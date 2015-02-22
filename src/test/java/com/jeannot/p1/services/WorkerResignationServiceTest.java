package com.jeannot.p1.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jeannot.p1.dto.Worker;
import com.jeannot.p1.dto.WorkerStatus;
import com.jeannot.p1.dto.WorkerType;
import com.jeannot.p1.services.impl.SimpleWorkerResignationService;

public class WorkerResignationServiceTest {
    
    private WorkerResignationService workerResignationService;
    

    @Before
    public void setup() throws Exception {
        workerResignationService = new SimpleWorkerResignationService();
    }
    

    @Test
    public void workerResignation() throws Exception {
        Worker worker = new Worker("Alan",WorkerType.MINOR, WorkerStatus.ACTIVE);
        Worker resignedWorker = workerResignationService.resign(worker);
        assertEquals(worker.getName(),resignedWorker.getName());
        assertEquals(worker.getWorkerType(),resignedWorker.getWorkerType());
        assertEquals(resignedWorker.getWorkerStatus(),WorkerStatus.RESIGNED);
    }
    
    @Test
    public void workerFired() throws Exception {
        Worker worker = new Worker("Alan",WorkerType.MAJOR, WorkerStatus.ACTIVE);
        Worker firedWorker = workerResignationService.fire(worker);
        assertEquals(worker.getName(),firedWorker.getName());
        assertEquals(worker.getWorkerType(),firedWorker.getWorkerType());
        assertEquals(firedWorker.getWorkerStatus(),WorkerStatus.FIRED);
    }
    

}
