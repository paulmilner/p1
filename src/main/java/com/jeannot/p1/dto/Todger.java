package com.jeannot.p1.dto;

public class Todger {
    
    private String name;
    private TodgerType todgerType;
    private TodgerStatus todgerStatus;
    
    @SuppressWarnings ("unused")
    private Todger() {
    }
    
    public Todger(String name, TodgerType todgerType, TodgerStatus todgerStatus) {
        this.name = name;
        this.todgerType = todgerType;
        this.todgerStatus = todgerStatus;
    }
    
    public String getName(){
        return name;
    }
    public TodgerType getTodgerType(){
        return todgerType;
    }

	@Override
    public String toString(){
        return "Todger [name=" + name + ", todgerType=" + todgerType + ", todgerStatus=" + todgerStatus + "]";
    }

    public TodgerStatus getTodgerStatus(){
        return todgerStatus;
    }
    
    
    
}
