package com.jeannot.p1.services;

import com.jeannot.p1.dto.Todger;

public interface TodgerResignationService {
    
    Todger resign(Todger todger);
    Todger fire(Todger todger);

}
