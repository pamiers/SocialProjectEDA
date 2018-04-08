package com.ilsan.robot.service;

import com.ilsan.robot.actorpool.Message;

import java.util.ArrayList;
import java.util.List;

public class MsgHistorySvc {
    private Message msg;
    private List<ExpectedAnswer> expectedAnswers;

    public MsgHistorySvc(Message msg) {
        this.msg = msg;
    }

    public List<String> getRobotMsg() {
        List<String> list = new ArrayList<String>();
        list.add("안녕하세요!!");
        list.add("일정이 지연되고 있습니다.");
        list.add("잘좀해!");
        return list;
    }

    public List<String> getManMsg() {
        List<String> list = new ArrayList<String>();
        list.add("안녕!!");
        list.add("알았어 임마.");
        list.add("너나 잘해");
        return list;
    }

    public List<Integer> getGap() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(60);
        list.add(150);
        list.add(10000);
        return list;
    }
}
