package com.jeannot.p1.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * ReST-friendly Exception which can be thrown from a service 
 *
 */
public class RestfulApplicationException extends WebApplicationException {
    
    private static final long serialVersionUID = 1L;

    public RestfulApplicationException(String message) {
        super(Response.status(Response.Status.BAD_REQUEST)
            .entity(message).type(MediaType.TEXT_PLAIN).build());
    }
}