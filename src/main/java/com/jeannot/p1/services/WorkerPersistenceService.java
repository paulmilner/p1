package com.jeannot.p1.services;

import java.util.Set;

import com.jeannot.p1.dto.Worker;

public interface WorkerPersistenceService {
    
    long create(Worker worker);
    
    Worker retrieve(long id);
    
    void update(long id, Worker worker);
    
    void delete(long id);
    
    int getCount();
    
    Set<Long> getKeys();

    Set<Worker> getValues();

}
