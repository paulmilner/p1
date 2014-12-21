package com.jeannot.p1;

import com.jeannot.p1.dto.Todger;
import com.jeannot.p1.dto.TodgerType;
import com.jeannot.p1.services.TodgerPromotionService;
import com.jeannot.p1.services.impl.SimpleTodgerPromotionService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        System.out.println( "Starting..." );
        
        Todger t = new Todger("Alan", TodgerType.MINOR);
        System.out.println(t.toString());
        System.out.println("Promoting todger...");
        
        TodgerPromotionService todgerPromotionService = new SimpleTodgerPromotionService();
        Todger t2 = todgerPromotionService.promoteTodger(t);
        System.out.println(t2.toString());

        System.out.println( "Finished." );
    }
}
