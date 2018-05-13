package com.ilsan.robot.service;

import kr.co.shineware.util.common.model.Pair;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
    private String type;
    private String subject;
    private String objective;
    private String verb;
    private String adjective;
    private String timeAdverb;
    private String placeAdverb;

    private List<Pair<String, String>> pairs = new ArrayList<>();

    public String getPlaceAdverb() {
        return placeAdverb;
    }

    public void setPlaceAdverb(String placeAdverb) {
        this.placeAdverb = placeAdverb;
    }

    public String getTimeAdverb() {
        return timeAdverb;
    }

    public void setTimeAdverb(String timeAdverb) {
        this.timeAdverb = timeAdverb;
    }

    public String getAdjective() {
        return adjective;
    }

    public void setAdjective(String adjective) {
        this.adjective = adjective;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Pair<String, String>> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair<String, String>> pairs) {
        this.pairs = pairs;
    }

    public String toString() {
        return "Subject: " + getSubject() +", Objective: " + getObjective() + ", Verb: " + getVerb() + ", Adjective: " + getAdjective();


    }
}
