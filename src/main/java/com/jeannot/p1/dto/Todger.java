package com.jeannot.p1.dto;

public class Todger {
    
    private String name;
    private TodgerType todgerType;
    
    @SuppressWarnings ("unused")
    private Todger() {
    }
    
    public Todger(String name, TodgerType todgerType) {
        this.name = name;
        this.todgerType = todgerType;
    }
    
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public TodgerType getTodgerType(){
        return todgerType;
    }
    public void setTodgerType(TodgerType todgerType){
        this.todgerType = todgerType;
    }

	@Override
	public String toString() {
		return "Todger [name=" + name + ", todgerType=" + todgerType + "]";
	}
    
    
    
}
