package com.ifedoroff.greenbee.model;

/**
 * Created by Rostik on 14.05.2017.
 */
public class PageDataResponseBody {
    String msg;
    String result;
    int temperature;
    int light;
    int humidity;

    public void setLight(int light) {
        this.light = light;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getLight() {
        return light;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
