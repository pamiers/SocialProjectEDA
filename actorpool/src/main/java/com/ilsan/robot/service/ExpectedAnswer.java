package com.ilsan.robot.service;

public class ExpectedAnswer {
    public int type;

    public boolean check(String msg) {
        int rand = (int) Math.random() * 100 + 1;
        if (0 < rand && 50 < rand) {
            return true;
        } else {
            return false;
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
