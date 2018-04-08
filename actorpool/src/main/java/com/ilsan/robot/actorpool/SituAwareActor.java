package com.ilsan.robot.actorpool;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class SituAwareActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef emotionActor;

    public SituAwareActor() {
        log.info("SituAwareActor constructor...");
        emotionActor = context().actorOf(Props.create(EmotionActor.class), "emotionActor");
    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String msg = (String) message;
            emotionActor.tell(msg, getSelf());
        }
    }
}
