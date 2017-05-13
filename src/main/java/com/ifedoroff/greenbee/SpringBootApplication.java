package com.ifedoroff.greenbee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifedoroff.greenbee.model.SensorData;
import com.ifedoroff.greenbee.service.DataHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

/**
 * Created by Rostik on 13.05.2017.
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

    public static ApplicationContext ctx;
    public static void main(String[] args) throws Exception {
        ctx = SpringApplication.run(SpringBootApplication.class, args);


        /*String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        */
        DataServer.run();
    }
}
