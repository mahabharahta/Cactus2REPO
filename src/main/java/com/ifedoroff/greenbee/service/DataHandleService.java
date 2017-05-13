package com.ifedoroff.greenbee.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifedoroff.greenbee.SpringBootApplication;
import com.ifedoroff.greenbee.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rostik on 14.05.2017.
 */
@Service
public class DataHandleService {



    public void addDataToBase(String data) throws  Exception
    {
        TemperatureDataRepository temrep = SpringBootApplication.ctx.getBean(TemperatureDataRepository.class);
        HumidityDataRepository humrep = SpringBootApplication.ctx.getBean(HumidityDataRepository.class);
        LightDataRepository ligrep = SpringBootApplication.ctx.getBean(LightDataRepository.class);

        String date = new java.util.Date().toString();
        ObjectMapper mapper = new ObjectMapper();
        SensorData obj = mapper.readValue(data,SensorData.class);


        Temperature temperature = new Temperature();
        temperature.setValue(obj.getTemperature());
        temperature.setDate(date);
        temperature.setUUID(obj.getId());
        temrep.insert(temperature);

        Humidity humidity = new Humidity();
        humidity.setValue(obj.getHumidity());
        humidity.setDate(date);
        humidity.setUUID(obj.getId());
        humrep.insert(humidity);

        Light light = new Light();
        light.setValue(obj.getLight());
        light.setDate(date);
        light.setUUID(obj.getId());
        ligrep.insert(light);
        System.out.println("success writed data");
    }
}
