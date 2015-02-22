package com.jeannot.p1.exception;

public class WorkerException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public WorkerException(String message) {
        super(message);
    }

}
