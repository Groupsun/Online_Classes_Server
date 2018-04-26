package com.scut.server.webSocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value = "/{role}/{openid}/{courserId}")
@Component
public class WebSocketServer {

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();


    private static ConcurrentHashMap<String,Class> classMap = new ConcurrentHashMap<>();
    private Class clas;
    private Session session;


    @OnOpen
    public void onOpen(Session session, @PathParam("courserId") String courserId,@PathParam("role") String role, @PathParam("openid") String openid) throws IOException {
        this.session = session;
        if(classMap.containsKey(courserId)){
                clas = classMap.get(courserId);
            }else{
                clas = new Class();
                classMap.put(courserId,clas);
            }

            if(role.equals("1")){
                clas.setTeacherSession(openid,session);
            }else if(role.equals("0")){
                clas.addStudentSession(openid,session);
            }
            webSocketSet.add(this);
    }


    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }


    @OnMessage
    public void onMessage(String message)throws IOException {
        System.out.println(message);
        for(WebSocketServer webSocketServer:webSocketSet){
            webSocketServer.session.getAsyncRemote().sendText(message);
        }
        //clas.sendMessage(message);
    }


    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }  
  

}  