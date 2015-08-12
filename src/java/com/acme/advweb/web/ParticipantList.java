
package com.acme.advweb.web;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.enterprise.context.ApplicationScoped;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;

@ApplicationScoped
public class ParticipantList {
    
    private final SseBroadcaster broadcaster =new SseBroadcaster();
    private String no;
    private  ReadWriteLock rwLock=new ReentrantReadWriteLock();
    
    
     public void add(EventOutput eo){
     final Lock wlock=rwLock.writeLock();
     wlock.lock();
     try{
         broadcaster.add(eo);
         }finally{
         wlock.unlock();
         }
    }
    
     public void send(OutboundEvent event,String no){
         System.out.println("IMPORTANT"+no);
         final Lock wlock=rwLock.writeLock();
     wlock.lock();
     try{
         broadcaster.broadcast(event);
         System.out.println("AFTER IMPORTANT"+no);
         }finally{
         wlock.unlock();
         System.out.println("AFTER"+no);
         }
     }

   
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

}
