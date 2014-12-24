package com.jeannot.p1.services.impl;

import com.jeannot.p1.dto.Todger;
import com.jeannot.p1.dto.TodgerStatus;
import com.jeannot.p1.dto.TodgerType;
import com.jeannot.p1.services.TodgerResignationService;

public class SimpleTodgerResignationService implements TodgerResignationService {

    public Todger resign(Todger todger){
        Todger resignedTodger = new Todger(todger.getName(),todger.getTodgerType(),TodgerStatus.RESIGNED);
        return resignedTodger;
    }

    public Todger fire(Todger todger){
        Todger firedTodger = new Todger(todger.getName(),todger.getTodgerType(),TodgerStatus.FIRED);
        return firedTodger;
    }

}
