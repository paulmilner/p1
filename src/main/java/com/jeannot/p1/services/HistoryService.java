package com.jeannot.p1.services;

import com.jeannot.p1.dto.Worker;

public interface HistoryService {
    
    public void addToHistory(Worker worker, String eventMessage);

    public void addToHistory(String string);

}
