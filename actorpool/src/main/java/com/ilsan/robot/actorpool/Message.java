package com.ilsan.robot.actorpool;

public interface Message {
    int RESPONSE_CHECKED = 1;

    String getMessage();
    int getStatus();
    String getId();
}
