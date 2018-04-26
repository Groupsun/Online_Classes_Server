package com.scut.server.webSocket;

public class Message {
    private int type = 1 ;
    private String content = "test";


    public Message() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
