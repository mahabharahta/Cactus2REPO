package com.ifedoroff.greenbee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Rostik on 13.05.2017.
 */
@Document(collection = "temperature")
public class Temperature implements IData{
    int value;

    public void setDate(String date) {
        this.date = date;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public int getValue() {
        return value;
    }

    public String getUUID() {
        return UUID;
    }

    String date;

    @Id
    String UUID;

    @Override
    public String toString() {
        return  "UUID : " + UUID + ", Date : " + date + ", Temperature Value :" + value;
    }
}