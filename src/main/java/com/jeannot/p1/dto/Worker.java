package com.jeannot.p1.dto;

import org.apache.commons.lang3.Validate;

public class Worker {
    
    private String name;
    private WorkerType workerType;
    private WorkerStatus workerStatus;
    
    @SuppressWarnings ("unused")
    private Worker() {
    }
    
    public Worker(String name, WorkerType workerType, WorkerStatus workerStatus) {
    	Validate.notNull(name);
        this.name = name;
        this.workerType = (workerType==null) ? WorkerType.MINOR : workerType;
        this.workerStatus = (workerStatus==null) ? WorkerStatus.DORMANT: workerStatus;
    }
    
    public String getName(){
        return name;
    }
    public WorkerType getWorkerType(){
        return workerType;
    }

	@Override
    public String toString(){
        return "Worker [name=" + name + ", workerType=" + workerType + ", workerStatus=" + workerStatus + "]";
    }

    public WorkerStatus getWorkerStatus(){
        return workerStatus;
    }
    
    
    
}
