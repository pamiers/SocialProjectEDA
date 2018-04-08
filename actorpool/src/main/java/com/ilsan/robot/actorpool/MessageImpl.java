package com.ilsan.robot.actorpool;

public class MessageImpl implements Message{

    private int status;

    public String getMessage() {
        return "Message!!!";
    }

    public int getStatus() {
        return status;
    }

    public String getId() {
        return "ID";
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
