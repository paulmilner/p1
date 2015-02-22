package com.jeannot.p1.services.impl;

import com.jeannot.p1.dto.Worker;
import com.jeannot.p1.dto.WorkerStatus;
import com.jeannot.p1.dto.WorkerType;
import com.jeannot.p1.services.WorkerResignationService;

public class SimpleWorkerResignationService implements WorkerResignationService {

    public Worker resign(Worker worker){
        Worker resignedWorker = new Worker(worker.getName(),worker.getWorkerType(),WorkerStatus.RESIGNED);
        return resignedWorker;
    }

    public Worker fire(Worker worker){
        Worker firedWorker = new Worker(worker.getName(),worker.getWorkerType(),WorkerStatus.FIRED);
        return firedWorker;
    }

}
