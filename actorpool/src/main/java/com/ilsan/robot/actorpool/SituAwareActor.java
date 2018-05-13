package com.ilsan.robot.actorpool;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.ilsan.robot.service.Sentence;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.util.common.model.Pair;

import java.util.ArrayList;
import java.util.List;

public class SituAwareActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef sentenceAnalyzer;

    public SituAwareActor() {
        log.info("SituAwareActor constructor...");
        sentenceAnalyzer = context().actorOf(Props.create(SentenceAnalyzer.class), "sentenceAnalyzer");
    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof KomoranResult) {
            KomoranResult result = (KomoranResult) message;
            // 문장의 형식 분류
            // 1 형식 : 주어 + 서술어
            // 2 형식 : 주어 + 보어 + 서술어
            // 3 형식 : 주어 + 목적어 + 서술어
            // 4 형식 : 주어 + 간목 + 직목 + 서술어
            // 5 형식 : 주어 + 목적보어 + 목적어 + 서술어
            List<Pair<String, String>> pair = result.getList();

            Sentence sentence = new Sentence();
            for (Pair p : pair) {
                sentence.getPairs().add(p);
                if(p.getSecond().equals("EF") || p.getSecond().equals("EC")) {
                    sentenceAnalyzer.tell(sentence, getSelf());
                    sentence = new Sentence();
                }
            }
        }
    }
}
