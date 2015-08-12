package com.acme.advweb.rest;

import com.acme.advweb.web.ParticipantList;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;

@RequestScoped
@Path("/chatroom/{no}")
public class ChatroomResource {
    
    @Inject private ParticipantList participants;
    
     @GET     
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    
    public Response connect(@PathParam("no") Integer no ) {
        
         System.out.println(">>> new connection "+no);
         EventOutput eo = new EventOutput();
         participants.add(eo);
         return (Response.ok(eo).build());
    }
    
}
