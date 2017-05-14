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

    public static boolean handleTemperatureError = false;
    public static boolean handleHumidityError = false;

    public void checkHumidity(Humidity humidity)
    {
        if (humidity.getValue() > 80 && !handleHumidityError)
        {
            handleHumidityError = true;
            NotificationRepository nr = SpringBootApplication.ctx.getBean(NotificationRepository.class);
            Notification notification = new Notification();
            notification.setMessage("Влажность превышает допустимую норму");
            notification.setDate(humidity.getDate());
            notification.setUuid(humidity.getUUID());
            notification.setName("Теплица 1");
            nr.insert(notification);
            MailService.send("rostik.nikolaenko@gmail.com",notification.getMessage());
        }



        if (humidity.getValue() <= 70 && !handleHumidityError)
        {
            handleHumidityError = true;
            NotificationRepository nr = SpringBootApplication.ctx.getBean(NotificationRepository.class);
            Notification notification = new Notification();
            notification.setMessage("Влажность ниже допустимой норму");
            notification.setDate(humidity.getDate());
            notification.setUuid(humidity.getUUID());
            notification.setName("Теплица 1");
            nr.insert(notification);
            MailService.send("rostik.nikolaenko@gmail.com",notification.getMessage());
        }

        if (humidity.getValue() >= 70 && humidity.getValue() <80 && handleHumidityError)
        {
            handleHumidityError = false;
            NotificationRepository nr = SpringBootApplication.ctx.getBean(NotificationRepository.class);
            Notification notification = new Notification();
            notification.setMessage("Влажность стабилизировалась");
            notification.setDate(humidity.getDate());
            notification.setUuid(humidity.getUUID());
            notification.setName("Теплица 1");
            nr.insert(notification);
            MailService.send("rostik.nikolaenko@gmail.com",notification.getMessage());
        }

    }

    public void checkTemperature(Temperature temperature)
    {
        if (temperature.getValue() > 26 && !handleTemperatureError)
        {
            handleTemperatureError = true;
            NotificationRepository nr = SpringBootApplication.ctx.getBean(NotificationRepository.class);
            Notification notification = new Notification();
            notification.setMessage("Темература превышает допустимую норму");
            notification.setDate(temperature.getDate());
            notification.setUuid(temperature.getUUID());
            notification.setName("Теплица 1");
            nr.insert(notification);
            MailService.send("rostik.nikolaenko@gmail.com",notification.getMessage());
        }



        if (temperature.getValue() <= 15 && !handleTemperatureError)
        {
            handleTemperatureError = true;
            NotificationRepository nr = SpringBootApplication.ctx.getBean(NotificationRepository.class);
            Notification notification = new Notification();
            notification.setMessage("Темература ниже допустимой норму");
            notification.setDate(temperature.getDate());
            notification.setUuid(temperature.getUUID());
            notification.setName("Теплица 1");
            nr.insert(notification);
            MailService.send("rostik.nikolaenko@gmail.com",notification.getMessage());
        }

        if (temperature.getValue() <= 26 && temperature.getValue() > 15 && handleTemperatureError)
        {
            handleTemperatureError = false;
            NotificationRepository nr = SpringBootApplication.ctx.getBean(NotificationRepository.class);
            Notification notification = new Notification();
            notification.setMessage("Темература стабилизировалась");
            notification.setDate(temperature.getDate());
            notification.setUuid(temperature.getUUID());
            notification.setName("Теплица 1");
            nr.insert(notification);
            MailService.send("rostik.nikolaenko@gmail.com",notification.getMessage());
        }

    }


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
        checkTemperature(temperature);
        temrep.insert(temperature);

        Humidity humidity = new Humidity();
        humidity.setValue(obj.getHumidity());
        humidity.setDate(date);
        humidity.setUUID(obj.getId());
        checkHumidity(humidity);
        humrep.insert(humidity);

        Light light = new Light();
        light.setValue(obj.getLight());
        light.setDate(date);
        light.setUUID(obj.getId());
        ligrep.insert(light);
        System.out.println("success writed data");
    }
}
