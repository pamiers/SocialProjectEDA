package com.ilsan.robot.actorpool;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class StopwordActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private Set<String> stopWords;
    private String path;

    private ActorRef textAnalysisActor;

    public StopwordActor() {
        init();
        textAnalysisActor = context().actorOf(Props.create(TextAnalysisActor.class), "textAnalysisActor");
    }

    public void init() {
        this.path = path;
        stopWords = new HashSet<>();
        File f = new File(path);
        File[] fList = f.listFiles();

        try {
            for (File file : fList) {
                Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()));
                lines.forEach(s -> stopWords.add(s.trim()));
                lines.close();
            }

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