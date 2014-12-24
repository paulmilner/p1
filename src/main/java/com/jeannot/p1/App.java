package com.jeannot.p1;

import com.jeannot.p1.dto.Todger;
import com.jeannot.p1.dto.TodgerStatus;
import com.jeannot.p1.dto.TodgerType;
import com.jeannot.p1.services.TodgerPromotionService;
import com.jeannot.p1.services.TodgerResignationService;
import com.jeannot.p1.services.impl.SimpleTodgerPromotionService;
import com.jeannot.p1.services.impl.SimpleTodgerResignationService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        System.out.println( "Starting..." );
        
        Todger t = new Todger("Alan", TodgerType.MINOR, TodgerStatus.THRUSTING);
        System.out.println(t.toString());
        System.out.println("Promoting todger...");
        
        TodgerPromotionService todgerPromotionService = new SimpleTodgerPromotionService();
        Todger t2 = todgerPromotionService.promoteTodger(t);
        System.out.println(t2.toString());

        TodgerResignationService todgerResignationService = new SimpleTodgerResignationService();
        System.out.println("Resigning todger...");
        Todger t3 = todgerResignationService.resign(t2);
        System.out.println(t3.toString());

        Todger t4 = new Todger("Andrew", TodgerType.MAJOR, TodgerStatus.THRUSTING);
        System.out.println(t4.toString());
        System.out.println("Firing todger...");
        Todger t5 = todgerResignationService.fire(t4);
        System.out.println(t5.toString());

        System.out.println( "Finished." );
    }
}
