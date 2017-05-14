package com.ifedoroff.greenbee.model;

/**
 * Created by Rostik on 14.05.2017.
 */
public class LightChart {
    String time;
    int lmt;

    public int getLmt() {
        return lmt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLmt(int tmp) {
        this.lmt = tmp;
    }
}
