package com.jeannot.p1.services.impl;

import java.util.Map;
import java.util.TreeMap;

import com.jeannot.p1.dto.Worker;

public class WorkerDatabase {
    
    private static WorkerDatabase me;
    
    private Map<Long,Worker> workers = new TreeMap<Long,Worker>(); //TreeMap is sorted by key
    private long nextId = 0;
    
    public WorkerDatabase() {
    }
    
    public static synchronized WorkerDatabase getInstance() {
        if (me==null) {
            me = new WorkerDatabase();
        }
        return me;
    }

    public synchronized Map<Long, Worker> getWorkers(){
        return workers;
    }

    public synchronized long getNextId(){
        nextId++;
        return nextId;
    }

}
