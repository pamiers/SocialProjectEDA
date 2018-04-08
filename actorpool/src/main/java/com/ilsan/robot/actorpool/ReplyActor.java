package com.ilsan.robot.actorpool;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ReplyActor extends UntypedActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public ReplyActor() {
        log.info("ReplyActor constructor...");
    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String msg = (String) message;
            log.info("{} received.", msg);
        }

    }
}
