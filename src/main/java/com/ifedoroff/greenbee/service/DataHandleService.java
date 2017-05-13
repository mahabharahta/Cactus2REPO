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

    public void addDataToBase(String data) throws  Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        SensorData obj = mapper.readValue(data,SensorData.class);
        Temperature temperature = new Temperature();
        temperature.setValue(obj.getTemperature());
        temperature.setDate(new java.util.Date().toString());
        //temperature.setUUID();
        temperatureDataRepository.insert(temperature);

        Humidity humidity = new Humidity();
        humidity.setValue(obj.getHumidity());
        humidity.setDate(new java.util.Date().toString());
        //humidity.setUUID();
        humidityDataRepository.insert(humidity);

    }
}
