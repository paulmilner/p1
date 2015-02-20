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

import com.jeannot.p1.dto.Todger;
import com.jeannot.p1.exception.TodgerPersistenceException;
import com.jeannot.p1.services.TodgerPersistenceService;

@Path("/todger")
public class RestfulTodgerPersistenceService implements TodgerPersistenceService {
    
    private TodgerDatabase todgerDatabase;
    
    public RestfulTodgerPersistenceService() {
        LOG.debug("constructed");
        todgerDatabase = TodgerDatabase.getInstance();
    }
    
    public static Logger LOG = Logger.getLogger(RestfulTodgerPersistenceService.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public synchronized long create(Todger todger){
        LOG.debug("create " + todger.toString());
        long id = todgerDatabase.getNextId();
        todgerDatabase.getTodgers().put(id,todger);
        return id;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Todger retrieve(long id){
        LOG.debug("get " + id);
        if (todgerDatabase.getTodgers().keySet().contains(id)) {
            return todgerDatabase.getTodgers().get(id);
        } else {
            throw new TodgerPersistenceException("Unable to retrieve, could not find todger with id=" + id);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public synchronized void update(long id, Todger todger){
        LOG.debug("update " + id + ":" + todger.toString());
        if (todgerDatabase.getTodgers().keySet().contains(id)) {
            todgerDatabase.getTodgers().put(id, todger);
        } else {
            throw new TodgerPersistenceException("Unable to update, could not find todger with id=" + id);
        }
    }

    @DELETE
    public synchronized void delete(long id){
        LOG.debug("delete " + id);
        if (todgerDatabase.getTodgers().keySet().contains(id)) {
            todgerDatabase.getTodgers().remove(id);
        } else {
            throw new TodgerPersistenceException("Unable to delete, could not find todger with id=" + id);
        }
    }
    
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public int getCount() {
        LOG.debug("count returning " + todgerDatabase.getTodgers().size());
        return todgerDatabase.getTodgers().size();
    }
    
    @GET
    @Path("/keys")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Long> getKeys() {
        LOG.debug("keys");
        return todgerDatabase.getTodgers().keySet();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Todger> getValues(){
        LOG.debug("values");
        Set<Todger> values = new HashSet<Todger>();
        for (Long id : todgerDatabase.getTodgers().keySet()) {
            Todger value = todgerDatabase.getTodgers().get(id);
            values.add(value);
        }
        return values;
    }

}
