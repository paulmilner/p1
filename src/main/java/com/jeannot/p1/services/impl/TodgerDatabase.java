package com.jeannot.p1.services.impl;

import java.util.Map;
import java.util.TreeMap;

import com.jeannot.p1.dto.Todger;

public class TodgerDatabase {
    
    private static TodgerDatabase me;
    
    private Map<Long,Todger> todgers = new TreeMap<Long,Todger>(); //TreeMap is sorted by key
    private long nextId = 0;
    
    public TodgerDatabase() {
    }
    
    public static synchronized TodgerDatabase getInstance() {
        if (me==null) {
            me = new TodgerDatabase();
        }
        return me;
    }

    public synchronized Map<Long, Todger> getTodgers(){
        return todgers;
    }

    public synchronized long getNextId(){
        nextId++;
        return nextId;
    }

}
