
package com.acme.advweb.rest;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;

@RequestScoped
@Path("/counter")
public class CounterResource {
    @Resource(lookup="concurrent/myFirstPool")
    
    private ManagedScheduledExecutorService service;
   
    @GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    //@Path("{start}")  path职能用pathparam,queryParam是读键值对，是读取后面的值
    public Response get(@DefaultValue("1") @QueryParam("start") Integer start){
        //System.out.println(">>>hhhhhhi");
        EventOutput eo=new EventOutput();
        CounterTask t=new CounterTask(start,eo);
        System.out.println(">>>hi");
        //so what is the difference between schedule and submit
        ScheduledFuture<?> f= service.scheduleAtFixedRate(t, 0, 1, TimeUnit.SECONDS);
        t.setHandle(f);
        return(Response.ok(eo).build()); 
    }
    
}
