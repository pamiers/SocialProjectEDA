package com.ilsan.robot.actorpool;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class EmotionActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef angerActor;
    private ActorRef happyActor;

    public EmotionActor() {
        log.info("EmotionActor constructor...");
        angerActor = context().actorOf(Props.create(AngerActor.class), "angerActor");
        happyActor = context().actorOf(Props.create(HappyActor.class), "happyActor");
    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof Message) {
            angerActor.tell(message, getSender());
            happyActor.tell(message, getSender());
        }
    }
}
