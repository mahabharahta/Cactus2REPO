package com.ifedoroff.greenbee.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifedoroff.greenbee.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Rostik on 13.05.2017.
 */
@Service
public class DataHandleService {


    @Autowired
    private TemperatureDataRepository temperatureDataRepository;

    @Autowired
    private HumidityDataRepository humidityDataRepository;

    @Autowired
    private LightDataRepository lightDataRepository;

    public void addDataToBase(String data) throws  Exception
    {
        String date = new java.util.Date().toString();
        ObjectMapper mapper = new ObjectMapper();
        SensorData obj = mapper.readValue(data,SensorData.class);

        Temperature temperature = new Temperature();
        temperature.setValue(obj.getTemperature());
        temperature.setDate(date);
        temperature.setUUID(obj.getId());
        temperatureDataRepository.insert(temperature);

        Humidity humidity = new Humidity();
        humidity.setValue(obj.getHumidity());
        humidity.setDate(date);
        humidity.setUUID(obj.getId());
        humidityDataRepository.insert(humidity);

        Light light = new Light();
        light.setValue(obj.getLight());
        light.setDate(date);
        light.setUUID(obj.getId());
        lightDataRepository.insert(light);

    }
}
