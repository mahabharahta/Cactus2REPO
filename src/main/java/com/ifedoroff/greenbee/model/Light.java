package com.ifedoroff.greenbee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Rostik on 13.05.2017.
 */
@Document(collection = "light")
public class Light implements IData {
    int value;

    String date;

    String UUID;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return  "UUID : " + UUID + ", Date : " + date + ", Light Value :" + value;
    }
}