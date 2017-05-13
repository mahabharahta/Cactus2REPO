package com.ifedoroff.greenbee.controller;

import com.ifedoroff.greenbee.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;
import javax.validation.Valid;
import java.sql.Date;

/**
 * Created by Rostik on 13.05.2017.
 */
@RestController
public class DataHandlerController {


    @Autowired
    HumidityDataRepository humidityDataRepository;

    @Autowired
    TemperatureDataRepository temperatureDataRepository;

   // @GetMapping("/api/data")
    public String getSearchResultViaAjax(@RequestBody SensorData data, Errors errors)
    {

        try
        {
            if (errors.hasErrors())
                return "bad_request";
            System.out.println("Here");
            System.out.println("Data confirmed. T = " + data.getTemperature() + ", H = " + data.getHumidity());
        /*Humidity humidity = new Humidity();
        humidity.setValue(data.getHumidity());
        humidityDataRepository.insert(humidity);

        Temperature temperature = new Temperature();
        temperature.setValue(data.getTemperature());
        temperatureDataRepository.insert(temperature);
        */
            return "succes";
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return  "bad";
        }
    }
}
