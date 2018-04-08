package com.ilsan.robot.actorpool;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class SocialPrjActor extends UntypedActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    ActorRef understandingActor;

    public SocialPrjActor() {
        understandingActor = context().actorOf(Props.create(UnderstandingActor.class), "understandingActor");
    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof Message) {
            String msg = ((Message) message).getMessage();
            understandingActor.tell(msg, getSelf());
        }

    }
}
