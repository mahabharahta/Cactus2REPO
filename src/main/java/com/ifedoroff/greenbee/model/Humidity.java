package com.ifedoroff.greenbee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Rostik on 13.05.2017.
 */
@Document(collection = "humidity")
public class Humidity {
    int value;

    String date;

    @Id
    String UUID;

    @Override
    public String toString() {
        return  "UUID : " + UUID + ", Date : " + date + ", Value :" + value;
    }
}