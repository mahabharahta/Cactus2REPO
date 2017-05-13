package com.ifedoroff.greenbee;

import org.springframework.boot.SpringApplication;

/**
 * Created by Rostik on 13.05.2017.
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootApplication.class, args);
        System.out.println("Starting");
    }
}
