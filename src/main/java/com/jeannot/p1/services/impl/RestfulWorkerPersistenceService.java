package com.jeannot.p1.services.impl;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.jeannot.p1.dto.Worker;
import com.jeannot.p1.exception.WorkerPersistenceException;
import com.jeannot.p1.services.WorkerPersistenceService;

@Path("/worker")
public class RestfulWorkerPersistenceService implements WorkerPersistenceService {
    
    private WorkerDatabase workerDatabase;
    
    public RestfulWorkerPersistenceService() {
        LOG.debug("constructed");
        workerDatabase = WorkerDatabase.getInstance();
    }
    
    public static Logger LOG = Logger.getLogger(RestfulWorkerPersistenceService.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public synchronized long create(Worker worker){
        LOG.debug("create " + worker.toString());
        long id = workerDatabase.getNextId();
        workerDatabase.getWorkers().put(id,worker);
        return id;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Worker retrieve(long id){
        LOG.debug("get " + id);
        if (workerDatabase.getWorkers().keySet().contains(id)) {
            return workerDatabase.getWorkers().get(id);
        } else {
            throw new WorkerPersistenceException("Unable to retrieve, could not find worker with id=" + id);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public synchronized void update(long id, Worker worker){
        LOG.debug("update " + id + ":" + worker.toString());
        if (workerDatabase.getWorkers().keySet().contains(id)) {
            workerDatabase.getWorkers().put(id, worker);
        } else {
            throw new WorkerPersistenceException("Unable to update, could not find worker with id=" + id);
        }
    }

    @DELETE
    public synchronized void delete(long id){
        LOG.debug("delete " + id);
        if (workerDatabase.getWorkers().keySet().contains(id)) {
            workerDatabase.getWorkers().remove(id);
        } else {
            throw new WorkerPersistenceException("Unable to delete, could not find worker with id=" + id);
        }
    }
    
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public int getCount() {
        LOG.debug("count returning " + workerDatabase.getWorkers().size());
        return workerDatabase.getWorkers().size();
    }
    
    @GET
    @Path("/keys")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Long> getKeys() {
        LOG.debug("keys");
        return workerDatabase.getWorkers().keySet();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Worker> getValues(){
        LOG.debug("values");
        Set<Worker> values = new HashSet<Worker>();
        for (Long id : workerDatabase.getWorkers().keySet()) {
            Worker value = workerDatabase.getWorkers().get(id);
            values.add(value);
        }
        return values;
    }

}
