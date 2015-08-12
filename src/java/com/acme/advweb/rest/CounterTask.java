
package com.acme.advweb.rest;

import java.io.IOException;
import java.util.concurrent.ScheduledFuture;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;


public class CounterTask implements Runnable{

    private int count;
    private final EventOutput eo;
    private ScheduledFuture<?>future =null;
    public CounterTask(int count,EventOutput eo){
        this.count=count;
        this.eo=eo;  
    }
    
    public void setHandle(ScheduledFuture<?> f){
        future=f;
    }
    @Override
    public void run() {
        //count++;
        OutboundEvent data=new OutboundEvent.Builder()
                .data(Integer.class, ++count)
                .build();
        try {
            eo.write(data);
        } catch (Exception ex) {
            System.out.println(">>>");
            future.cancel(true);
        }
        System.out.println(">>>count"+count);
    }
    
}
