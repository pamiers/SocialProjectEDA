package com.ilsan.robot.actorpool;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class AngerActor extends UntypedActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef noResponseActor;
    private ActorRef slowRespActor;
    private ActorRef wrongRespActor;
    private ActorRef humorActor;

    public AngerActor() {
        noResponseActor = context().actorOf(Props.create(NoResponseActor.class), "noResponseActor");
        slowRespActor = context().actorOf(Props.create(SlowRespActor.class), "slowRespActor");
        wrongRespActor = context().actorOf(Props.create(WrongRespActor.class), "wrongRespActor");
        humorActor = context().actorOf(Props.create(HumorActor.class), "humorActor");
    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof Message) {
            if (((Message) message).getStatus() != Message.RESPONSE_CHECKED) {
                noResponseActor.tell(message, getSelf());
                slowRespActor.tell(message, getSelf());
                wrongRespActor.tell(message, getSelf());
            } else {
                humorActor.tell(message, getSelf());
            }
        }
    }
}
