package com.jeannot.p1.services.impl;

import com.jeannot.p1.dto.Todger;
import com.jeannot.p1.dto.TodgerType;
import com.jeannot.p1.exception.TodgerException;
import com.jeannot.p1.services.TodgerPromotionService;

public class SimpleTodgerPromotionService implements TodgerPromotionService {

    public Todger promoteTodger(Todger todger) throws TodgerException {
        TodgerType todgerType = todger.getTodgerType();
        if (todgerType==TodgerType.SUPREME) {
            throw new TodgerException("Cannot promote this todger. Is already at max todger.");
        } else {
            int nextTodgerTypeOrdinal = todgerType.ordinal()+1;
            todgerType = TodgerType.values()[nextTodgerTypeOrdinal];
        }
        Todger promotedTodger = new Todger(todger.getName(),todgerType);
        return promotedTodger;
    }

}
