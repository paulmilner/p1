package com.jeannot.p1.services.impl;

import com.jeannot.p1.dto.Worker;
import com.jeannot.p1.dto.WorkerType;
import com.jeannot.p1.exception.WorkerException;
import com.jeannot.p1.services.WorkerPromotionService;

public class SimpleWorkerPromotionService implements WorkerPromotionService {

    public Worker promoteWorker(Worker worker) throws WorkerException {
        WorkerType workerType = worker.getWorkerType();
        if (workerType==WorkerType.SUPREME) {
            throw new WorkerException("Cannot promote this worker. Is already at max worker.");
        } else {
            int nextWorkerTypeOrdinal = workerType.ordinal()+1;
            workerType = WorkerType.values()[nextWorkerTypeOrdinal];
        }
        Worker promotedWorker = new Worker(worker.getName(),workerType, worker.getWorkerStatus());
        return promotedWorker;
    }

}
