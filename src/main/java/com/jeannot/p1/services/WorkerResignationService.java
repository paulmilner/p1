package com.jeannot.p1.services;

import com.jeannot.p1.dto.Worker;

public interface WorkerResignationService {
    
    Worker resign(Worker worker);
    Worker fire(Worker worker);

}
