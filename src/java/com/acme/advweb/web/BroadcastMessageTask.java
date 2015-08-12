
package com.acme.advweb.web;


import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.sse.OutboundEvent;

public class BroadcastMessageTask implements Runnable{

    private String name;
    private String msg;
    private ParticipantList participants;
    private String no;
    
    public BroadcastMessageTask(String name,String msg,ParticipantList participants,String no){
        this.name=name;
        this.msg=msg;
        this.participants=participants;
        this.no=no;
    }
    
    @Override
    public void run() {
        
       System.out.println(">>>> 11"+new Date().getTime());
       JsonObject json= Json.createObjectBuilder()
                .add("name",name)
                .add("msg", msg)
                .build();
        System.out.println(">>>> 20"+new Date().getTime());
        OutboundEvent data=new OutboundEvent.Builder()
                .data(JsonObject.class,json)
                .mediaType(MediaType.APPLICATION_JSON_TYPE)
                .name(no)
                .build();
        System.out.println(">>>> 22"+new Date().getTime());
         participants.send(data,no);
    }
    
}
