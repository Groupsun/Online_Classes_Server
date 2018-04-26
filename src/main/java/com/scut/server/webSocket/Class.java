package com.scut.server.webSocket;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Class {

    private String couserId;
    private String teacherOpenid;
    private Session teacherSession;

    private Map<String,Session> studentSessionMap = new HashMap<>();

    public synchronized void setTeacherSession(String openid,Session session){
        this.teacherOpenid = openid;
        this.teacherSession = session;
    }

    public synchronized void addStudentSession(String openid,Session session){
        this.studentSessionMap.put(openid, session);
    }

    public void sendMessage(String message) throws IOException {
        for(Session session: studentSessionMap.values()){
            session.getBasicRemote().sendText(message);
            System.out.println("send" + message);
        }
    }
}
