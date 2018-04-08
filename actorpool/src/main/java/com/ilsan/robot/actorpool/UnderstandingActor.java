package com.ilsan.robot.actorpool;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class UnderstandingActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef situAwareActor;
    private ActorRef relDefineActor;

    public UnderstandingActor() {
        situAwareActor = context().actorOf(Props.create(SituAwareActor.class), "situAwareActor");
        relDefineActor = context().actorOf(Props.create(RelDefineActor.class), "relDefineActor");
    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String msg = (String) message;

            stopword();
            textAnalysis();
            textJuice();

            situAwareActor.tell(msg, getSender());
            relDefineActor.tell(msg, getSender());
        }
    }

    private void textJuice() {
        log.info("make juice....");
    }

    private void textAnalysis() {
        log.info("working textAnalysis....");
    }

    private void stopword() {
        log.info("working stopword.....");
    }
}
