package com.jeannot.p1.services;

import java.util.Set;

import com.jeannot.p1.dto.Todger;

public interface TodgerPersistenceService {
    
    long create(Todger todger);
    
    Todger retrieve(long id);
    
    void update(long id, Todger todger);
    
    void delete(long id);
    
    int getCount();
    
    Set<Long> getKeys();

    Set<Todger> getValues();

}
