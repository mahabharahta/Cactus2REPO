package com.ifedoroff.greenbee.model;

/**
 * Created by Rostik on 14.05.2017.
 */
public class TemperatureChart {
    String time;
    int tmp;

    public int getTmp() {
        return tmp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTmp(int tmp) {
        this.tmp = tmp;
    }
}
