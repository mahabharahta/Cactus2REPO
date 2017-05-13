package com.ifedoroff.greenbee.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Rostik on 13.05.2017.
 */
public class SensorData {
    @NotBlank(message = " temperature can't be empty")
    int temperature;

    @NotBlank(message = "humidity can't be empty")
    int humidity;

    @NotBlank(message = "light can't be empty")
    int light;

    @NotBlank(message = "id can't be empty")
    String id;


    public void setId(String id) {
        this.id = id;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getLight() {
        return light;
    }

    public String getId() {
        return id;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
