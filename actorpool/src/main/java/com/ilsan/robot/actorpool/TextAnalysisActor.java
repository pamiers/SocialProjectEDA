package com.ilsan.robot.actorpool;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

public class TextAnalysisActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private Komoran komoran;
    private ActorRef situAwareActor;

    public TextAnalysisActor() {
        init();
        situAwareActor = context().actorOf(Props.create(SituAwareActor.class), "situAwareActor");
    }

    public void init() {
        komoran = new Komoran(DEFAULT_MODEL.FULL);
        komoran.setFWDic("/Users/hosik/IdeaProjects/SocialProjectEDA/actorpool/src/main/resources/user_data/fwd.user");
        komoran.setUserDic("/Users/hosik/IdeaProjects/SocialProjectEDA/actorpool/src/main/resources/user_data/dic.user");
    }

    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof String) {
            String input = (String) message;

            KomoranResult analyzeResultList = komoran.analyze(input);
            situAwareActor.tell(analyzeResultList, getSelf());

//            List<Token> tokenList = analyzeResultList.getTokenList();
//
//            //print each tokens by getTokenList()
//            System.out.println("==========print 'getTokenList()'==========");
//            for (Token token : tokenList) {
//                System.out.println(token);
//                System.out.println(token.getMorph()+"/"+token.getPos()+"("+token.getBeginIndex()+","+token.getEndIndex()+")");
//                System.out.println();
//            }

        }
    }
}
