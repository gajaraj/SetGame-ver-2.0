
package com.acme.advweb.web;


import java.util.Date;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.sse.OutboundEvent;

@WebServlet("/newMessage")
public class NewMessageServlet extends HttpServlet{
    @Inject ParticipantList participantlists;
    
    @Resource(lookup="concurrent/myFirstPool")
    private ManagedScheduledExecutorService service;
    
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response){
        String name=request.getParameter("name");
        String msg=request.getParameter("msg");
        String no=request.getParameter("no");
        
        
        System.out.println("NO>>>2"+new Date().getTime()+"attention"+no);
        service.submit(new BroadcastMessageTask(name,msg,participantlists,no));
        
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
