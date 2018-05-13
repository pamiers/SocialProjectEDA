package com.ilsan.robot.actorpool;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class StopwordActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private List<String> stopWords;
    private String path;

    private ActorRef textAnalysisActor;

    public StopwordActor() {
        init();
        textAnalysisActor = context().actorOf(Props.create(TextAnalysisActor.class), "textAnalysisActor");
    }

    public void init() {
        this.path = "/Users/hosik/IdeaProjects/SocialProjectEDA/actorpool/src/main/resources/StopWord/kor-stopword.txt";
        stopWords = new ArrayList<>();
        File f = new File(path);
        File[] fList = f.listFiles();

        try {
            stopWords = Files.readAllLines(Paths.get(path),
                    StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }


    }


    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String msg = (String) message;
            String[] tokens = msg.split(" ");
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            for (String word : tokens) {
                if (stopWords.contains(word.trim())) {
                    cnt++;
                    continue;
                } else {
                    if (cnt < tokens.length) {
                        sb.append(word + " ");
                        cnt++;
                    } else {
                        sb.append(word);
                    }
                }
            }

            textAnalysisActor.tell(sb.toString(), getSelf());

        }

    }
}