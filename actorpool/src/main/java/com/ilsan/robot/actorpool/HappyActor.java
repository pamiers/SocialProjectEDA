package com.ilsan.robot.actorpool;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class HappyActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void onReceive(Object o) throws Exception {
        log.info("happy called");
    }
}
