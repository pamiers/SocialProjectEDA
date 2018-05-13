package com.ilsan.robot.actorpool;

public class MessageImpl implements Message{

    private int status;

    public String getMessage() {
        return "인텔리제이 최고에요. 이클립스 구려요.";
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
