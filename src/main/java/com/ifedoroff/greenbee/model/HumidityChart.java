package com.ifedoroff.greenbee.model;

/**
 * Created by Rostik on 14.05.2017.
 */
public class HumidityChart {
    String time;
    int hmd;

    public int getHmd() {
        return hmd;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setHmd(int tmp) {
        this.hmd = tmp;
    }
}
