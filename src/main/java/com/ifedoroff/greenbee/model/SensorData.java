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
