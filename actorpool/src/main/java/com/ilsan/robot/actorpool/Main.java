package com.ilsan.robot.actorpool;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("SocialProject");
        ActorRef ping = actorSystem.actorOf(Props.create(SocialPrjActor.class), "socialPrjActor");

        Message msg = new MessageImpl();
        ping.tell(msg, ActorRef.noSender());



    }
}
