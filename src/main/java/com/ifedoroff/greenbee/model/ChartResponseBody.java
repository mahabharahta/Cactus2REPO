package com.ifedoroff.greenbee.model;

import java.util.List;

/**
 * Created by Rostik on 14.05.2017.
 */
public class ChartResponseBody {
    String msg;
    List<Temperature> temperatures;
    List<Light> lights;
    List<Humidity> humidities;

    public List<Humidity> getHumidities() {
        return humidities;
    }

    public List<Light> getLights() {
        return lights;
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    public void setHumidities(List<Humidity> humidities) {
        this.humidities = humidities;
    }

    public void setLights(List<Light> lights) {
        this.lights = lights;
    }

    public void setTemperatures(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
