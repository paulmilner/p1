package com.jeannot.p1.exception;

public class WorkerPersistenceException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public WorkerPersistenceException(String message) {
        super(message);
    }

}
