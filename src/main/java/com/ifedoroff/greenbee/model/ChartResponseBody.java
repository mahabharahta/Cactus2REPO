package com.ifedoroff.greenbee.model;

import java.util.List;

/**
 * Created by Rostik on 14.05.2017.
 */
public class ChartResponseBody {
    String msg;
    List<TemperatureChart> temperatures;
    List<LightChart> lights;
    List<HumidityChart> humidities;

    public List<HumidityChart> getHumidities() {
        return humidities;
    }

    public List<LightChart> getLights() {
        return lights;
    }

    public List<TemperatureChart> getTemperatures() {
        return temperatures;
    }

    public void setHumilities(List<HumidityChart> humidities) {
        this.humidities = humidities;
    }

    public void setLights(List<LightChart> lights) {
        this.lights = lights;
    }

    public void setTemperatures(List<TemperatureChart> temperatures) {
        this.temperatures = temperatures;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
