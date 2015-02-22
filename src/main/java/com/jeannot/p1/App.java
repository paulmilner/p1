package com.jeannot.p1;

import com.jeannot.p1.dto.Worker;
import com.jeannot.p1.dto.WorkerStatus;
import com.jeannot.p1.dto.WorkerType;
import com.jeannot.p1.services.WorkerPromotionService;
import com.jeannot.p1.services.WorkerResignationService;
import com.jeannot.p1.services.impl.SimpleWorkerPromotionService;
import com.jeannot.p1.services.impl.SimpleWorkerResignationService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        System.out.println( "Starting..." );
        
        Worker t = new Worker("Alan", WorkerType.MINOR, WorkerStatus.ACTIVE);
        System.out.println(t.toString());
        System.out.println("Promoting worker...");
        
        WorkerPromotionService workerPromotionService = new SimpleWorkerPromotionService();
        Worker t2 = workerPromotionService.promoteWorker(t);
        System.out.println(t2.toString());

        WorkerResignationService workerResignationService = new SimpleWorkerResignationService();
        System.out.println("Resigning worker...");
        Worker t3 = workerResignationService.resign(t2);
        System.out.println(t3.toString());

        Worker t4 = new Worker("Andrew", WorkerType.MAJOR, WorkerStatus.ACTIVE);
        System.out.println(t4.toString());
        System.out.println("Firing worker...");
        Worker t5 = workerResignationService.fire(t4);
        System.out.println(t5.toString());

        System.out.println( "Finished." );
    }
}
