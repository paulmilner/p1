package com.jeannot.p1.services.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.jeannot.p1.dto.Todger;
import com.jeannot.p1.exception.TodgerPersistenceException;
import com.jeannot.p1.services.TodgerPersistenceService;

public class SimpleTodgerPersistenceService implements TodgerPersistenceService {
    
    Map<Long,Todger> todgers = new TreeMap<Long,Todger>(); //TreeMap is sorted by key
    long nextId = 0;

    public synchronized long create(Todger todger){
        nextId++;
        long id = nextId;
        todgers.put(id,todger);
        return id;
    }

    public Todger retrieve(long id){
        if (todgers.keySet().contains(id)) {
            return todgers.get(id);
        } else {
            throw new TodgerPersistenceException("Unable to retrieve, could not find todger with id=" + id);
        }
    }

    public synchronized void update(long id, Todger todger){
        if (todgers.keySet().contains(id)) {
            todgers.put(id, todger);
        } else {
            throw new TodgerPersistenceException("Unable to update, could not find todger with id=" + id);
        }
    }

    public synchronized void delete(long id){
        if (todgers.keySet().contains(id)) {
            todgers.remove(id);
        } else {
            throw new TodgerPersistenceException("Unable to delete, could not find todger with id=" + id);
        }
    }
    
    public int getCount() {
        return todgers.size();
    }
    
    public Set<Long> getKeys() {
        return todgers.keySet();
    }

    public Set<Todger> getValues(){
        Set<Todger> values = new HashSet<Todger>();
        for (Long id : todgers.keySet()) {
            Todger value = todgers.get(id);
            values.add(value);
        }
        return values;
    }

}
