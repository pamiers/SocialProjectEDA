package com.ilsan.robot.actorpool;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class RelDefineActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef replyActor;

    public RelDefineActor() {
        log.info("RelDefineActor constructor...");
        replyActor = context().actorOf(Props.create(ReplyActor.class), "replyActor");
    }
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String msg = (String) message;
            replyActor.tell(msg, getSender());
        }
    }
}
