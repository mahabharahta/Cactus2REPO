package com.ifedoroff.greenbee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Rostik on 13.05.2017.
 */
@Document(collection = "temperature")
public class Temperature{
    int value;

    String Date;

    @Id
    String UUID;
}