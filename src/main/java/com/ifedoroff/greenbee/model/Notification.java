package com.ifedoroff.greenbee.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Rostik on 14.05.2017.
 */
@Document(collection = "notifications")
public class Notification {
    String name;
    String message;
    String uuid;
    String date;

    public String getDate()
    {
        return  date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
