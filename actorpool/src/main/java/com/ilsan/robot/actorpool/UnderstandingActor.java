package com.ilsan.robot.actorpool;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.resume;
import static akka.actor.SupervisorStrategy.stop;

public class UnderstandingActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef situAwareActor;
    private ActorRef relDefineActor;

    private ActorRef stopwordActor;

    public UnderstandingActor() {
        situAwareActor = context().actorOf(Props.create(SituAwareActor.class), "situAwareActor");
        relDefineActor = context().actorOf(Props.create(RelDefineActor.class), "relDefineActor");
        stopwordActor = context().actorOf(Props.create(StopwordActor.class), "stopwordActor");
    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String msg = (String) message;

            stopword(msg);
//            textAnalysis();
//            textJuice();    // 추출
            // TODO: 의도파악

//            situAwareActor.tell(msg, getSender());
//            relDefineActor.tell(msg, getSender());
        }
    }

    private static SupervisorStrategy strategy =
            new OneForOneStrategy(10, Duration.create("1 minute"),
                    new Function<Throwable, SupervisorStrategy.Directive>() {
                        @Override
                        public SupervisorStrategy.Directive apply(Throwable t) throws Exception {
                            if (t instanceof ArithmeticException) {
                                return resume();
                            } else if (t instanceof NullPointerException) {
                                return restart();
                            } else if (t instanceof IllegalArgumentException) {
                                return stop();
                            } else {
                                return escalate();
                            }
                        }

                    });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }


    private void textJuice() {
        log.info("make juice....");
    }

    private void textAnalysis() {
        log.info("working textAnalysis....");
    }

    private void stopword(String msg) {
        stopwordActor.tell(msg, getSelf());

        log.info("working stopword.....");
    }
}
